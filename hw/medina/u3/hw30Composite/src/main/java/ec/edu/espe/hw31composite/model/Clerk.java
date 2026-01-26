package ec.edu.espe.hw31composite.model;

/**
 *
 * @author Joseph B. Medina
 */
public class Clerk extends Employee{
    
    public Clerk(String aName){
        this.name = aName;
        this.title = "Clerk";
        
    }
    
    public void stateName(){
        super.stateName();
    }
    
}
