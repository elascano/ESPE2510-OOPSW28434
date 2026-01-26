package ec.edu.espe.composite.model;

/**
 *
 * @author Maryuri Quiña, @ESPE
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

    public void stateName() {
        super.stateName();
    }
}
