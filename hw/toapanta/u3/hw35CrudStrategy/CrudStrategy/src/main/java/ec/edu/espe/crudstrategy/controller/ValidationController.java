package ec.edu.espe.crudstrategy.controller;

/**
 *
 * @author Adrian Toapanta 
 */
public class ValidationController {

    public static boolean validateName(String name) {
        return name != null && name.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    public static boolean validateApartmentNumber(String apartmentNumber) {
        return apartmentNumber != null && apartmentNumber.matches("^[a-zA-Z0-9\\-]+$");
    }

    public static boolean validateEmail(String email) {
        return email != null && email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean validatePhone(String phone) {
        return phone != null && phone.matches("^\\d{7,15}$");
    }

    public static String validateAllFields(String name,
            String email, String phone) {
        StringBuilder errors = new StringBuilder();

        if (!validateName(name)) {
            errors.append("• Name can only contain letters and spaces.\n");
        }

        if (!validateEmail(email)) {
            errors.append("• Email must be in a valid format (example@domain.com).\n");
        }

        if (!validatePhone(phone)) {
            errors.append("• Telephone can only contain numbers (7-15 digits).\n");
        }

        return errors.toString();
    }
}
