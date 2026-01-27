package ec.edu.espe.ustaxcalculator.model;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
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
    public float salesTotal(float amount) {
    return amount + (amount * taxPercentage) / 100;
    }
    public float getSingletonData() {
    return taxPercentage;
    }
}