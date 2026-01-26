package ec.edu.espe.composite.model;
/**
 *
 * @author Emily Calle, @ESPE
 */

public class Clerk extends Employee {
    public Clerk(String aName) {
        this();
        this.name = aName;
    }

    public Clerk() {
        this.title = "Clerk";
    }
}