package ec.edu.espe.guifactory.model;

/**
 *
 * @author Pablo Collaguazo
 */
public class LinuxFactory extends GUIFactory{

        
    public  Button createButton(){
        
        return (new  LinuxButton());
    }
    
    public Menu createMenu(){
        return (new LinuxMenu());
    }
}
