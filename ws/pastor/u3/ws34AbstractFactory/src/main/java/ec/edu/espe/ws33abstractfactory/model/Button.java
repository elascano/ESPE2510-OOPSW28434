package ec.edu.espe.ws33abstractfactory.model;
/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public abstract class Button {
    
    public String caption;

    public Button() {
    }

    @Override
    public String toString() {
        return "Button: "  ;
    }

    public abstract void print();
    
}
