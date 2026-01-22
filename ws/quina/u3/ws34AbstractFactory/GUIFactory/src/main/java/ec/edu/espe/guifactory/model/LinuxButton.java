package ec.edu.espe.guifactory.model;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class LinuxButton extends Button {
    public void paint(){
        System.out.println("I'm a LinuxButton: " + caption);
    }
}
