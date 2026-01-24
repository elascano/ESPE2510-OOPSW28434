package ec.edu.espe.ws33abstractfactory.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public abstract class Menu {
    
    public String caption;

    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu: " ;
    }
    
    public abstract void paint();

}
