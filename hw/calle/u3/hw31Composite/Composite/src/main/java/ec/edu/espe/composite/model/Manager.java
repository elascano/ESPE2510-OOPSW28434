package ec.edu.espe.composite.model;
/**
 *
 * @author Emily Calle, @ESPE
 */


public class Manager extends Supervisor {
    public Manager(String aName) {
        this();
        this.name = aName;
    }

    public Manager() {
        super();
        this.title = "Manager";
    }

    @Override
    public void stateName() {
        super.stateName();
    }
}