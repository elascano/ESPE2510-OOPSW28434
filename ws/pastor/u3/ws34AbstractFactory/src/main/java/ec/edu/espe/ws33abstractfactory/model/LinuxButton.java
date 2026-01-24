package ec.edu.espe.ws33abstractfactory.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class LinuxButton extends Button {

    public LinuxButton() {
    }

    @Override
    public String toString() {
        return super.toString() + "I'm a LinuxButton: " + caption; 
    }

    @Override
    public void print() {
        System.out.println(toString());
    }
    
    
}
