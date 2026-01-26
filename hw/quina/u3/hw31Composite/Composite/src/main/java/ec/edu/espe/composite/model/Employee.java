
package ec.edu.espe.composite.model;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public abstract class Employee {
    String name = "not assigned yet";
    String title = "not assigned yet";
    
    public void stateName(){
        System.out.println(title + " " + name);
    }
}
