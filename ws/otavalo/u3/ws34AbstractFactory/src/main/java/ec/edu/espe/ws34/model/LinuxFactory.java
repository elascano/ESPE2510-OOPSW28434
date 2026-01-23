package ec.edu.espe.ws34.model;

/**
 *
 * @author Arelys Otavalo
 */
public class LinuxFactory extends GUIFactory {
    public Button createButton(){
        return (new LinuxButton());
        
    }
    
    public Menu createMenu(){
        return(new LinuxMenu());
    }

}
