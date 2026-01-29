package ec.edu.espe.composite.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class Teller extends Employee {

    public Teller(String aName) {
        this();
        name = aName;
    }

    public Teller() {
        title = "Teller";
    }
}