package ec.edu.espe.composite.model;

/**
 *
 * @author Mateo Cevallos, Object Masters
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
