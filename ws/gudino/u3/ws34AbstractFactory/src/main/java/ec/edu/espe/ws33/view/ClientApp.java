package ec.edu.espe.ws33.view;

import ec.edu.espe.ws33.model.GUIFactory;
import ec.edu.espe.ws33.model.Button;
import ec.edu.espe.ws33.model.Menu;

public class ClientApp {
    public static void main(String[] args) {
        GUIFactory aFactory = GUIFactory.getFactory();
        Button aButton = aFactory.createButton();
        aButton.caption = "Play";
        aButton.paint();
        
        Menu aMenu = aFactory.createMenu();
        aMenu.caption = "File";
        aMenu.paint();
    }
}