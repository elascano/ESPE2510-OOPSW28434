package ec.edu.espe.ustaxcalculator;

/**
 *
 * @author Joseph B. Medina
 */
public class USTax {

    private static USTax instance;

    private float taxPercentage;

    private USTax() {
        taxPercentage = 0.0f;
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

    public float getSingletonData() {
        return taxPercentage;
    }

    public float salesTotal(float amount) {
        return amount + (amount * taxPercentage);
    }
}
