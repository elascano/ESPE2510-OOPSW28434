package ec.edu.espe.hwcomposite.model;

public class Manager extends Supervisor {
    public Manager(String aName) {
        this();
        name = aName;
    }

    public Manager() {
        title = "Manager";
    }

    @Override
    public void stateName() {
        super.stateName(); // Composite behavior
    }
}
