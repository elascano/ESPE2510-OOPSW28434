package ec.edu.espe.giufactory.model;

/**
 *
 * @author Arelis Bonilla,The Art of Programming, @ESPE
 */
public class LinuxFactory extends GUIFactory { 
    public Button createButton() {
        return(new LinuxButton());
    }
    public Menu createMenu() { 
        return(new LinuxMenu());
    }
}