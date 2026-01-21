package ec.edu.espe.giufactory.view;

import ec.edu.espe.giufactory.model.Button;
import ec.edu.espe.giufactory.model.GUIFactory;


/**
 *
 * @author Arelis Bonilla,The Art of Programming, @ESPE
 */
public class ClientApp {
    public static void main(String[] args) {
        GUIFactory aFactory = GUIFactory.getFactory(); 
        Button aButton = aFactory.createButton(); 
        aButton.caption = "Play";
        aButton.paint();
    }
}
