package ec.edu.espe.composite.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class Manager extends Supervisor {

    public Manager(String aName) {
        this();
        name = aName;
    }

    public Manager() {
        title = "Manager";
    }
}