
package ec.edu.espe.guifactory.view;
import ec.edu.espe.guifactory.model.GUIFactory;
import ec.edu.espe.guifactory.model.Button;

/**
 *
 * @author Emily Calle, @ESPE
 */
public class ClientApp {

    public static void main(String[] args) {
        GUIFactory aFactory = GUIFactory.getFactory();
        Button aButton = aFactory.createButton();
        aButton.caption = "Play";
        aButton.paint();
    }
}
