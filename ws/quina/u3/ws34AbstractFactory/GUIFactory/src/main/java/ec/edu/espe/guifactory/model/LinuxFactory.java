package ec.edu.espe.guifactory.model;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class LinuxFactory extends GUIFactory {
    public Button createButton(){
        return ( new LinuxButton());
    }
    public Menu createMenu(){
        return ( new LinuxMenu());
    }
}
