package ec.edu.espe.ws33abstractfactory.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class LinuxFactory extends GUIFactory {

    @Override
    public Button createButton() {
        return (new WinButton());
    }

    @Override
    public Menu createMenu() {
        return (new WinMenu());
    }
}
