
package ec.edu.espe.composite.model;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class Teller extends Employee {
    
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
