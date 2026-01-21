package ec.edu.espe.guifactory.model;
/**
 *
 * @author Emily Calle, @ESPE
 */
public class LinuxButton extends Button {

    @Override
    public void paint() {
        System.out.println("I'm a LinuxButton: " + caption);
    }
}
