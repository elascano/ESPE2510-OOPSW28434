package ec.edu.espe.guifactory.view;

import ec.edu.espe.guifactory.model.Button;
import ec.edu.espe.guifactory.model.GUIFactory;

/**
 *
 * @author Steven Loza
 */
public class ClientApp {
    public static void main(String[] args) {
        GUIFactory aFactory = GUIFactory.getFactory();
        Button aButton = aFactory.createButton();
        aButton.caption = "Play";
        aButton.paint();
    }
    
}
