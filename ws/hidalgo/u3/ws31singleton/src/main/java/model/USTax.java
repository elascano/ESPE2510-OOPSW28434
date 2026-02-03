
package model;

/**
 *
 * @author Mikae Hidalgo, Object Masters, @ESPE
 */
public class USTax {
    private static USTax instance;
    private float taxPercentage;

    private USTax() {
        this.taxPercentage = 15f; 
    }


    public static USTax getInstance() {
        if (instance == null) {
            instance = new USTax();
        }
        return instance;
    }


    public float getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(float taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public float calculateSalesTotal(float amount) {
        return amount + (amount * taxPercentage);
    }
}
