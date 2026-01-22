package ec.edu.espe.guifactory.model;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class WinFactory extends GUIFactory{
    public Button createButton(){
        return( new WinButton());
    }
    public Menu createMenu(){
        return ( new WinMenu());
    }
}
