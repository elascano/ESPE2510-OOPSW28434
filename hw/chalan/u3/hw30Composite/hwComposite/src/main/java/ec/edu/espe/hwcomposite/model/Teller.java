package ec.edu.espe.hwcomposite.model;

public class Teller extends Employee {
    public Teller(String aName) {
        this();
        name = aName;
    }

    public Teller() {
        title = "Teller";
    }

    @Override
    public void stateName() {
        super.stateName(); // Leaf behavior
    }
}
