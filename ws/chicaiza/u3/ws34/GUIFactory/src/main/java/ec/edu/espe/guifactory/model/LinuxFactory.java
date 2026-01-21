package ec.edu.espe.guifactory.model;

/**
 *
 * @author Steven Loza
 */
public class LinuxFactory extends GUIFactory{

        
    public  Button createButton(){
        
        return (new  LinuxButton());
    }
    
    public Menu createMenu(){
        return (new LinuxMenu());
    }
}
