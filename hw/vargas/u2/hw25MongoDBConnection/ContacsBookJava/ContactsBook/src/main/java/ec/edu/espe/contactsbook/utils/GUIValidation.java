package ec.edu.espe.contactsbook.utils;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class GUIValidation {

    private static final Color DEFAULT_BACKGROUND = Color.WHITE;
    private static final Color ERROR_BACKGROUND = new Color(255, 192, 192);

    public static boolean validateEmptyField(JTextField textField, String fieldName) {
        String value = textField.getText();
        boolean isValid = (value != null && !value.trim().isEmpty());

        if (!isValid) {
            textField.setBackground(ERROR_BACKGROUND);
            JOptionPane.showMessageDialog(null, "The '" + fieldName + "' field cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } else {
            textField.setBackground(DEFAULT_BACKGROUND);
        }
        return isValid;
    }

    public static boolean validateNameAndLastname(JTextField textField, String fieldName) {
        String name = textField.getText();
        boolean isValid = name.matches("^[\\p{L} .'-]+$");

        if (!isValid) {
            textField.setBackground(ERROR_BACKGROUND);
            JOptionPane.showMessageDialog(null, "The '" + fieldName + "' field contains invalid characters. " + 
                    "Only letters, spaces, dots, and hyphens are allowed."
                    , "Validation Error", JOptionPane.ERROR_MESSAGE);
        } else {
            textField.setBackground(DEFAULT_BACKGROUND);
        }
        return isValid;
    }

    public static boolean validateFutureDate(JDateChooser dateChooser) {
        Date date = dateChooser.getDate();

        if (date == null) {
            dateChooser.getDateEditor().getUiComponent().setBackground(ERROR_BACKGROUND);
            JOptionPane.showMessageDialog(null, "The date field cannot be empty.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Calendar hoy = Calendar.getInstance();

        hoy.set(Calendar.HOUR_OF_DAY, 23);
        hoy.set(Calendar.MINUTE, 59);
        hoy.set(Calendar.SECOND, 59);
        hoy.set(Calendar.MILLISECOND, 999);

        boolean esValido = date.before(hoy.getTime());

        if (!esValido) {
            dateChooser.getDateEditor().getUiComponent().setBackground(ERROR_BACKGROUND);
            JOptionPane.showMessageDialog(null, "The selected date cannot be a future date.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
        } else {
            dateChooser.getDateEditor().getUiComponent().setBackground(DEFAULT_BACKGROUND);
        }
        return esValido;
    }
}
