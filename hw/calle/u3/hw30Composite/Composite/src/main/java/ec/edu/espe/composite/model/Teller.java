package ec.edu.espe.composite.model;
/**
 *
 * @author Emily Calle, @ESPE
 */

public class Teller extends Employee {
    public Teller(String aName) {
        this();
        this.name = aName;
    }

    public Teller() {
        this.title = "Teller";
    }
}