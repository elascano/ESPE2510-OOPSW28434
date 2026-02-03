package ec.edu.espe.hw31composite.model;

/**
 *
 * @author Joseph B. Medina
 */
public class Teller extends Employee {
    
    public Teller(String aName){
        this.name = aName;
        this.title = "Teller";
        
    }
    
    @Override
    public void stateName (){
        super.stateName();
    }
    
}
