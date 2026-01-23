package ec.edu.espe.composite.model;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public  abstract class Employee {
    String name = "not assigned yet";
    String title = "not assigned yet";
    
    public void stateName(){
        System.out.println(title + " " + name);
    }
}
            
