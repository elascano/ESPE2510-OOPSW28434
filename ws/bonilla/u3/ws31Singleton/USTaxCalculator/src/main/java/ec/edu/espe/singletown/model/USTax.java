package ec.edu.espe.singletown.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
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

    public float getTaxPercentage() {
        return taxPercentage;
    }

    public float salesTotal(float sale) {
        return sale + (sale * taxPercentage / 100);
    }

    public String getSingletonData() {
        return "USTax Singleton - Tax: " + taxPercentage + "%";
    }
}