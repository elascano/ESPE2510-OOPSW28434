package ec.edu.espe.ws33abstractfactory.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class WinMenu extends Menu{

    public WinMenu() {
    }

    @Override
    public String toString() {
        return super.toString() + "I'm a WinMenu: " + caption;
    }

    @Override
    public void paint() {
        System.out.println(toString());
    }
    
    
    
}
