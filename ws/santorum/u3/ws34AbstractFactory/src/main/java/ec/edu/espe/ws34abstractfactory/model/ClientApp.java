
package ec.edu.espe.ws34abstractfactory.model;



/**
 *
 * @author Thais Santorum
 */
class ClientApp {
    public static void main (String[] args){
        GUIFactory aFactory = GUIFactory.getFactory();
        Button aButton = aFactory.createButton();
        aButton.caption = "Play";
        aButton.paint();
    }
}
