package ec.edu.espe.q38_51.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class G implements H {

    private J helper;

    public G(J helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        System.out.println("G executing service...");
        helper.assist();
    }
}