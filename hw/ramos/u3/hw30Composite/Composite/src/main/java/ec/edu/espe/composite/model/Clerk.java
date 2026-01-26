package ec.edu.espe.composite.model;

/**
 *
 * @author Paulo Ramos
 */
public class Clerk extends Employee {
    public Clerk (String aName){
        this();
        name = aName;
    }
    
    public void stateName(){
        super.stateName();
    }
    
    public Clerk(){
        tittle = "Clerk";
    }
    
}
