package ec.edu.espe.hw31composite.model;

/**
 *
 * @author Joseph B. Medina
 */
public class President extends Supervisor {
    
    private static President president = new President();
    
    private President (String aName){
        this.name = aName;
        this.title = "President";
        
    }
    
    private President (){
        super();
        title = "President"; 
        
    }
    
    public void stateName(){
        super.stateName();
        
    }
    
    public static President getPresident (String aName) {
        president.name = aName;
        return President.president;
        
    }
    
    
}
