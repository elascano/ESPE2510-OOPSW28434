package ec.edu.espe.composite.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class Clerk extends Employee {

    public Clerk(String aName) {
        this();
        name = aName;
    }

    public Clerk() {
        title = "Clerk";
    }
}