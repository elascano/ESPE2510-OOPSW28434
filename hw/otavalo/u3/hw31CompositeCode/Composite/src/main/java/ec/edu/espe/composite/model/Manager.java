package ec.edu.espe.composite.model;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class Manager extends Supervisor{
    public Manager(String aName){
        this();
        name = aName;
    } 
    
    public Manager(){
        super();
        title = "Manager";        
    }
    
    public void stateName(){
        super.stateName();
    }
}
