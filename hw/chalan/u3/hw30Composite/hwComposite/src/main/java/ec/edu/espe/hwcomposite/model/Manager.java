package ec.edu.espe.hwcomposite.model;
/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public class Manager extends Supervisor {
    public Manager(String aName) {
        this();
        name = aName;
    }

    public Manager() {
        super();
        title = "Manager";
    }

    @Override
    public void stateName() {
        super.stateName();
    }
}