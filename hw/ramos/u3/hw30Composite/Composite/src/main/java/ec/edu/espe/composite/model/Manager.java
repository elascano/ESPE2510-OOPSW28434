package ec.edu.espe.composite.model;

/**
 *
 * @author Paulo Ramos
 */
public class Manager extends Supervisor{
    public Manager(String aName){
        this();
        name = aName;
    }
    
    public Manager(){
        super();
        tittle = "Manger";
    }
    
    @Override
    public void stateName(){
        
        super.stateName();
    }
}
