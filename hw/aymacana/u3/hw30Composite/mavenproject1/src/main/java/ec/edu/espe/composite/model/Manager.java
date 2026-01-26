package ec.edu.espe.composite.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
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
