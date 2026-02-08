package model;

/**
 *
 * @author Bryan Gudino, KNOWLEDGE ENCAPSULATE, @ESPE
 */
public class ProductValidator {
    
        // PRODUCT NAME
    public static boolean isValidProductName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        if (name.length() > 20) {
            return false;
        }

        return name.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");
    }

    // BASE PRICE
    public static boolean isValidBasePrice(String price) {
        if (price == null || price.trim().isEmpty()) {
            return false;
        }

        return price.matches("^\\d+([.,]\\d{1,2})?$");
    }

    public static double parseBasePrice(String price) {
        price = price.replace(",", ".");
        return Double.parseDouble(price);
    }

    // STOCK
    public static boolean isValidStock(int stock) {
        return stock > 0 && stock < 50;
    }
}
