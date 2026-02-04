package ec.edu.espe.ws34abstractfactory.model;

/**
 *
 * @author Joseph Medina
 */
class LinuxFactory extends GUIFactory {
    public Button createButton() {
        return(new LinuxButton());
    }
    public Menu createMenu() {
        return (new LinuxMenu());
    }
}
