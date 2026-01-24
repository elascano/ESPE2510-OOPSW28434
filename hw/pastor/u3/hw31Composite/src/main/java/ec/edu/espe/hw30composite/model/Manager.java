package ec.edu.espe.hw30composite.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Manager extends Supervisor {
    
    public Manager(String aName){
        this.name = aName;
        this.title = "Manager";
    }
 
    public void stateName(){
        super.stateName();
    }
}
