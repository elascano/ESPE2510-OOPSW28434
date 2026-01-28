package ec.edu.espe.composite.model;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
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
        title = "Clerk";
    }
}
