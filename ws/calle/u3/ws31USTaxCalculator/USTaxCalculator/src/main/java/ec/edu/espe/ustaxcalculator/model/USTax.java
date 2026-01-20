package ec.edu.espe.ustaxcalculator.model;
/**
 *
 * @author Emily Calle, @ESPE
 */
public class USTax {
    private static USTax instance;
    private float taxPercentage;

    private USTax() {
        this.taxPercentage = 0.15f;
    }

    public static USTax getInstance() {
        if (instance == null) {
            instance = new USTax();
        }
        return instance;
    }

    public void setTaxPercentage(float tax) {
        this.taxPercentage = tax;
    }

    public double salesTotal(double amount) {
        return amount + (amount * taxPercentage);
    }

    public String getSingletonData() {
        return "TAX: " + (taxPercentage * 100) + "%";
    }
}