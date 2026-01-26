package ec.edu.espe.hwcomposite.model;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public abstract class Employee {
    protected String name = "not assigned yet";
    protected String title = "not assigned yet";

    public void stateName() {
        System.out.println(title + " " + name);
    }
    
}