package ec.edu.espe.hw30composite.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Teller extends Employee{
    public Teller(String aName){
        this.name = aName;
        this.title = "Teller";
    }
    
    public void stateName(){
        super.stateName();
    }
}
