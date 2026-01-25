package ec.edu.espe.composite.model;

/**
 *
 * @author Daniel
 */
public abstract class Employee {
    String name = "not assigned yet";
    String title = "not assigned yet";

    public void stateName() {
        System.out.println(title + " " + name);
    }
    
}
