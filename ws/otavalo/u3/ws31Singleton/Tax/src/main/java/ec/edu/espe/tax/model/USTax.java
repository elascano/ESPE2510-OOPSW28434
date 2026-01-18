package ec.edu.espe.tax.model;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class USTax {

    private static USTax instance;
    private float taxPercentage;

    private USTax() {
    }

    public float salesTotal(float amount) {
        return amount * (1 + taxPercentage);
    }

    public static USTax getInstance() {
        if (instance == null) {
            instance = new USTax();
        }
        return instance;
    }

    public static void setInstance(USTax instance) {
        USTax.instance = instance;
    }

    public void setTaxPercentage(float tax) {
        this.taxPercentage = tax;
    }

    public float getTaxPercentage() {
        return this.taxPercentage;
    }

    public float getSingletonData() {
        return this.taxPercentage;
    }

}
