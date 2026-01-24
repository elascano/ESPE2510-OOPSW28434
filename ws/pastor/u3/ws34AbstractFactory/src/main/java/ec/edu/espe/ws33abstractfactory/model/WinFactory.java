package ec.edu.espe.ws33abstractfactory.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class WinFactory extends GUIFactory{
    @Override
    public Button createButton() {
        return(new WinButton()); 
    }

    @Override
    public Menu createMenu() {
        return (new WinMenu());
    }
}
