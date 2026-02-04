package ec.edu.espe.giufactory.model;

/**
 *
 * @author Arelis Bonilla,The Art of Programming, @ESPE
 */
public class WinFactory extends GUIFactory { 
    public Button createButton() {
        return(new WinButton());
    }
    public Menu createMenu() { 
        return(new WinMenu());
    }
}
