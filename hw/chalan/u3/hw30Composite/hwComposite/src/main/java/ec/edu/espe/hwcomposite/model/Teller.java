package ec.edu.espe.hwcomposite.model;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
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
        super.stateName();
    }
}