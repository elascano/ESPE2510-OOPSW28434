package ec.edu.espe.composite.model;

/**
 *
 * @author Emily Calle, @ESPE
 */

public abstract class Employee {
    protected String name = "not assigned yet";
    protected String title = "not assigned yet";

    public void stateName() {
        System.out.println(title + " " + name);
    }
}