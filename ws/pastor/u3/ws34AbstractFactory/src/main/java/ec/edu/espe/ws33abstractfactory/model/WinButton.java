package ec.edu.espe.ws33abstractfactory.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class WinButton extends Button{

    public WinButton() {
    }

    @Override
    public String toString() {
        return super.toString() + "I'm a WinButton: " + caption;
    }

    @Override
    public void print() {
        System.out.println(toString());
    }
    
    
}
