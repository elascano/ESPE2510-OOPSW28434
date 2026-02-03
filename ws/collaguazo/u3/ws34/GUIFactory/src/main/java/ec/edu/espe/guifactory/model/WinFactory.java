package ec.edu.espe.guifactory.model;

/**
 *
 * @author Pablo Collaguazo
 */
public class WinFactory extends GUIFactory{
    
    public  Button createButton(){
        return (new  WinButton());
    }
    
    public Menu createMenu(){
        return (new WinMenu());
    }
    

}
