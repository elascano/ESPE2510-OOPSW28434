package ec.edu.espe.guifactory.model;

/**
 *
 * @author Steven Loza
 */
public class WinFactory extends GUIFactory{
    
    public  Button createButton(){
        return (new  WinButton());
    }
    
    public Menu createMenu(){
        return (new WinMenu());
    }
    

}
