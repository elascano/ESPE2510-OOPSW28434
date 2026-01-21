
package ec.edu.espe.guifactory.model;

/**
 *
 * @author Emily Calle, @ESPE
 */

public class LinuxFactory extends GUIFactory {

    public Button createButton() {
        return (new LinuxButton());
    }

    public Menu createMenu() {
        return (new LinuxMenu());
    }
}