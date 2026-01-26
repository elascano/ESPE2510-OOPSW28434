package ec.edu.espe.hwcomposite.model;

public class President extends Supervisor {
    private static President president = new President(); // Singleton

    private President(String aName) {
        this();
        name = aName;
    }

    private President() {
        title = "President";
    }

    @Override
    public void stateName() {
       super.stateName(); // Composite behavior
    }

    public static President getPresident(String aName) {
        president.name = aName;
        return president; // Single instance
    }
}
