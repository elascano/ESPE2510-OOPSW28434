package ec.edu.espe.ws34abstractfactory.model;

/**
 *
 * @author Joseph Medina
 */
public class WinButton extends Button {
    public void paint(){
        System.out.println("Im a WinButton" + caption);
    }
}
