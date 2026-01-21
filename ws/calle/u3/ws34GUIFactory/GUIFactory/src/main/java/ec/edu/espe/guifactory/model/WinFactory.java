
package ec.edu.espe.guifactory.model;

/**
 *
 * @author Emily Calle, @ESPE
 */

 public class WinFactory extends GUIFactory {

    public Button createButton() {
        return (new WinButton());
    }

  
    public Menu createMenu() {
        return(new WinMenu());
    }
}


