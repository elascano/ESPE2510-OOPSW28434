
package ec.edu.espe.ws34.model;

/**
 *
 * @author Mikael Hidalgo
 */
public class WinFactory extends GUIFactory {
    public Button createButton(){
        return (new WinButton());       
    }
    
    public Menu createMenu(){
        return (new WinMenu());
        
    }
   
}
