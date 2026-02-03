package ec.edu.espe.ws34abstractfactory.model;

/**
 *
 * @author Thais Santorum
 */
class LinuxFactory extends GUIFactory {
    public Button createButton() {
        return(new LinuxButton());
    }
    public Menu createMenu() {
        return (new LinuxMenu());
    }
}
