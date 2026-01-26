package ec.edu.espe.hwcomposite.model;

public class Clerk extends Employee {
    public Clerk(String aName) {
        this();
        name = aName;
    }

    public Clerk() {
        title = "Clerk";
    }

    @Override
    public void stateName() {
        super.stateName();
    }
}
