package ec.edu.espe.ws33abstractfactory.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class LinuxMenu extends Menu{

    public LinuxMenu() {
    }

    @Override
    public String toString() {
        return super.toString() + "I'm a LinuxMenu: " + caption;
    }

    @Override
    public void paint() {
        System.out.println(toString());
    }
    
    
}
