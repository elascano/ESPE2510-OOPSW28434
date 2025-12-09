package utils;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.JRadioButton;
/**
 *
 * @author JOSUE
 */
public class Validator {
    public static boolean validateName(String text) {
        return text != null && text.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
    }
    // TODO: compute the age based on the BirthDate
    public static int computeAge(LocalDate birthDate) {
        if (birthDate != null) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        }
        return 0;
    }

    public static String getSelectedSex(JRadioButton male, JRadioButton female) {
        if (male.isSelected()) {
            return "Male";
        } else if (female.isSelected()) {
            return "Female";
        } else {
            return "Unknown";
        }
    }

    // TODO: code a loop to add all the hobbies
    public static ArrayList<String> getSelectedHobbies(JList<String> lstHobbies) {
        ArrayList<String> hobbies = new ArrayList<>();
        List<String> selectedValues = lstHobbies.getSelectedValuesList();
        hobbies.addAll(selectedValues);
        return hobbies;
    }
}
