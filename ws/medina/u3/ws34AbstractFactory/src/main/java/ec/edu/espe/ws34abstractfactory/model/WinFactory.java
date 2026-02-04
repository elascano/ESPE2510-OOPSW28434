
package ec.edu.espe.ws34abstractfactory.model;

/**
 *
 * @author Joseph Medina
 */
class WinFactory extends GUIFactory {
    public Button createButton() {
        return (new WinButton());
    }
    public Menu createMenu() {
        return (new WinMenu());
    }
}
