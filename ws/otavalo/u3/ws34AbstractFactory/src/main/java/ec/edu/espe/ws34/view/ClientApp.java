
package ec.edu.espe.ws34.view;

import ec.edu.espe.ws34.model.Button;
import ec.edu.espe.ws34.model.GUIFactory;
import ec.edu.espe.ws34.model.Menu;

/**
 *
 * @author Arelys Otavalo
 */
public class ClientApp {
    public static void main(String[] args){
        GUIFactory aFactory = GUIFactory.getFactory();
        Button aButton = aFactory.createButton();
        aButton.caption = "Play";
        aButton.paint();
        Menu aMenu = aFactory.createMenu();
        aMenu.caption = "Play";
        aMenu.paint();
    }
}
