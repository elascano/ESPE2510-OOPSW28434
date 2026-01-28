package ec.edu.espe.composite.model;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class Teller extends Employee{
    public Teller(String aName){
        this();
        name = aName;
    }
    
    public void stateName(){
        super.stateName();
    }
    
    public Teller(){
        title = "Teller";
    }
}
