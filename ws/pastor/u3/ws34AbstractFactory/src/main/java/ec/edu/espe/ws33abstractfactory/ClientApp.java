package ec.edu.espe.ws33abstractfactory;

import ec.edu.espe.ws33abstractfactory.model.Button;
import ec.edu.espe.ws33abstractfactory.model.GUIFactory;

/**
 *
 * @author The POOwer Rangers of Programming, Student OOP, @ESPE
 */
public class ClientApp {

    public static void main(String[] args) {
        GUIFactory aFactory = GUIFactory.getFactory();
        Button aButton = aFactory.createButton();
        aButton.caption = "Play";
        aButton.print();
    }
}
