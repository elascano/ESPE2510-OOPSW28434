package ec.edu.espe.ustaxcalculator.model;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class USTax {

    private static USTax instance;
    private float taxPercentage;

    private USTax() {
    }

    public static USTax getInstance() {
        if (instance == null) {
            instance = new USTax();
        }
        return instance;
    }

    public void setTaxPercentage(float taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public float getSingletonData() {
        return taxPercentage;
    }

    public float salesTotal(float amount) {
        float total;
        total = amount + (amount*taxPercentage)/100;
        return total;
    }
}
