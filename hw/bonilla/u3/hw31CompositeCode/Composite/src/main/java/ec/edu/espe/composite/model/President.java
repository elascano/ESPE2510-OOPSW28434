package ec.edu.espe.composite.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class President extends Supervisor {

    private static President president = new President();

    private President() {
        title = "President";
    }

    public static President getPresident(String aName) {
        president.name = aName;
        return president;
    }
}