package ec.edu.espe.hw30composite.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Clerk extends Employee {
    public Clerk(String aName){
        this.name = aName;
        this.title = "Clerk";
    }
    
    public void stateName(){
        super.stateName();
    }
}
