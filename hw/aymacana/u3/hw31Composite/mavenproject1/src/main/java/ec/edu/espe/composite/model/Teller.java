package ec.edu.espe.composite.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
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