package ec.edu.espe.composite.model;
/**
 *
 * @author Emily Calle, @ESPE
 */

public class President extends Supervisor {
    private static President president = new President();

    private President(String aName) {
        this();
        this.name = aName;
    }

    private President() {
        super();
        this.title = "President";
    }

    public static President getPresident(String aName) {
        president.name = aName;
        return president;
    }

    @Override
    public void stateName() {
        super.stateName();
    }
}