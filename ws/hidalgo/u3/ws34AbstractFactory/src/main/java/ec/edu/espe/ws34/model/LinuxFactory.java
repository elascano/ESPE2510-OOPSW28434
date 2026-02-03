package ec.edu.espe.ws34.model;

/**
 *
 * @author Mikael Hidalgo
public class LinuxFactory extends GUIFactory {
    public Button createButton(){
        return (new LinuxButton());
        
    }
    
    public Menu createMenu(){
        return(new LinuxMenu());
    }

}
