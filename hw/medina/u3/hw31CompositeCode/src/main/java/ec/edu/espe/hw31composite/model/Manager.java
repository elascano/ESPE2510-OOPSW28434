package ec.edu.espe.hw31composite.model;

/**
 *
 * @author Joseph B. Medina
 */
public class Manager extends Supervisor {
    
    public Manager(String aName){
        this.name = aName;
        this.title = "Manager";
    }
    
    public void stateName () {
        super.stateName();
        
    }
    
}
