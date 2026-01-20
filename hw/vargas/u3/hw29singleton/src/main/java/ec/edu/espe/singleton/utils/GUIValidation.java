package ec.edu.espe.singleton.utils;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class GUIValidation {

    private static final Color COLOR_ERROR = new Color(255, 200, 200); 
    private static final Color COLOR_OK = Color.WHITE;

    public static boolean validateNotEmpty(JTextComponent txt, String fieldName) {
        if (txt.getText().trim().isEmpty()) {
            showError(txt, "El campo '" + fieldName + "' es obligatorio.");
            return false;
        }
        markSuccess(txt);
        return true;
    }

    public static boolean validateOnlyLetters(JTextField txt, String fieldName) {
    if (txt.getText().trim().isEmpty()) {
        showError(txt, "El campo '" + fieldName + "' es obligatorio.");
        return false;
    }

    if (!txt.getText().trim().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
        showError(txt, "El campo '" + fieldName + "' solo debe contener letras.");
        return false;
    }

    markSuccess(txt);
    return true;
}

    public static boolean validateOnlyNumbers(JTextField txt, String fieldName) {
        if (txt.getText().trim().isEmpty()) {
            showError(txt, "El campo '" + fieldName + "' es obligatorio.");
            return false;
        }

        if (!txt.getText().trim().matches("[0-9]+")) {
            showError(txt, "El campo '" + fieldName + "' debe contener solo números.");
            return false;
        }
        markSuccess(txt);
        return true;
    }

    public static boolean validateLetterKey(
        char c,
        JTextComponent txt,
        String fieldName
    ) {
    if (!Character.isLetter(c) && c != ' ') {
        txt.setBackground(new Color(255, 200, 200));
        JOptionPane.showMessageDialog(
            txt,
            "El campo '" + fieldName + "' solo acepta letras",
            "Error",
            JOptionPane.WARNING_MESSAGE
        );
        return false;
    }
    txt.setBackground(Color.WHITE);
    return true;
}

    public static boolean validateNumberKey(
        char c,
        JTextComponent txt,
        String fieldName
    ) {
    if (!Character.isDigit(c)) {
        txt.setBackground(new Color(255, 200, 200));
        JOptionPane.showMessageDialog(
            txt,
            "El campo '" + fieldName + "' solo acepta números",
            "Error",
            JOptionPane.WARNING_MESSAGE
        );
        return false;
    }
    txt.setBackground(Color.WHITE);
    return true;
}

    
    public static boolean validateNumericLength(JTextField txt, String fieldName, int length) {
        if (!validateOnlyNumbers(txt, fieldName)) return false;

        if (txt.getText().trim().length() != length) {
            showError(txt, "El campo '" + fieldName + "' debe tener exactamente " + length + " dígitos.");
            return false;
        }
        markSuccess(txt);
        return true;
    }

    public static boolean validatePositiveInt(JTextField txt, String fieldName) {
        if (!validateOnlyNumbers(txt, fieldName)) return false;

        try {
            int valor = Integer.parseInt(txt.getText().trim());
            if (valor <= 0) {
                showError(txt, "El campo '" + fieldName + "' debe ser mayor a 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            showError(txt, "El número en '" + fieldName + "' es inválido.");
            return false;
        }
        markSuccess(txt);
        return true;
    }

    public static boolean validateDateRange(JDateChooser dateChooser, String fieldName) {
        if (dateChooser.getDate() == null) {
            showErrorDate(dateChooser, "Debes seleccionar una fecha para: " + fieldName);
            return false;
        }

        Date selectedDate = dateChooser.getDate();
        Date today = new Date();
        
        Date cleanSelected = removeTime(selectedDate);
        Date cleanToday = removeTime(today);

        if (cleanSelected.before(cleanToday)) {
            showErrorDate(dateChooser, "La fecha no puede ser anterior a hoy.");
            return false;
        }

        Calendar calSelected = Calendar.getInstance();
        calSelected.setTime(cleanSelected);
        Calendar calToday = Calendar.getInstance();
        calToday.setTime(cleanToday);
        
        if (calSelected.get(Calendar.YEAR) > (calToday.get(Calendar.YEAR) + 1)) {
             showErrorDate(dateChooser, "La fecha no puede exceder el próximo año.");
             return false;
        }

        markSuccessDate(dateChooser);
        return true;
    }
    public static boolean validateEmail(JTextField txt, String fieldName) {
        if (!validateNotEmpty(txt, fieldName)) return false;

        String text = txt.getText().trim();
        
        if (!text.matches("^[^@]+@[^@]+\\.com$")) {
            showError(txt, "El '" + fieldName + "' debe contener '@' y terminar en '.com'");
            return false;
        }

        markSuccess(txt);
        return true;
    }
    public static boolean validateFutureDate(JDateChooser dateChooser) {
        Date date = dateChooser.getDate();

        if (date == null) {
            showErrorDate(dateChooser, "El cambo fecha de nacimiento no puede estar vacío");
            return false;
        }

        Calendar today = Calendar.getInstance();

        today.set(Calendar.HOUR_OF_DAY, 23);
        today.set(Calendar.MINUTE, 59);
        today.set(Calendar.SECOND, 59);
        today.set(Calendar.MILLISECOND, 999);

        boolean isValid = date.before(today.getTime());

        if (!isValid) {
            showErrorDate(dateChooser, "La fecha no puede ser una fecha futura");
        } else {
            markSuccessDate(dateChooser);
        }
        return isValid;
    }

    private static void showError(JTextComponent component, String message) {
        component.setBackground(COLOR_ERROR);
        JOptionPane.showMessageDialog(component, message, "Error", JOptionPane.WARNING_MESSAGE);
        component.requestFocus();
    }
    
    private static void markSuccess(JTextComponent component) {
        component.setBackground(COLOR_OK);
    }

    private static void showErrorDate(JDateChooser chooser, String message) {
        chooser.getDateEditor().getUiComponent().setBackground(COLOR_ERROR);
        JOptionPane.showMessageDialog(chooser, message, "Error", JOptionPane.WARNING_MESSAGE);
        chooser.requestFocus();
    }

    private static void markSuccessDate(JDateChooser chooser) {
         chooser.getDateEditor().getUiComponent().setBackground(COLOR_OK);
    }
    
    private static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
