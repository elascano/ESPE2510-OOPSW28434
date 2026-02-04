package ec.edu.espe.guifactory.model;

import ec.edu.espe.guifactory.model.Button;
import ec.edu.espe.guifactory.model.GUIFactory;
import ec.edu.espe.guifactory.model.GUIFactory;
/**
 *
 * @author Thais Santorum
 */

class ClientApp {
    public static void main(String[] args) {
        GUIFactory aFactory = GUIFactory.getFactory();
        Button aButton = aFactory.createButton();

        aButton.caption = "Play";
        aButton.paint();
    }
 }  
