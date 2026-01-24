package ec.edu.espe.hw30composite.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public abstract class Employee {
    String name = "not assinged yet";
    String title = "not assigned yet";
    
    public void stateName(){
        System.out.println(title + " " + name);
    }
}
