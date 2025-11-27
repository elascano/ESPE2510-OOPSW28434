package ec.edu.espe.elevator.model;

/**
 *
 * @author Maryuri Quiña
 */
public class UpButoon extends Button {

    public UpButoon(String nameButton) {
        super(nameButton);
    }

    public void callElevator() {
        System.out.println("The elevator is arriving");
    }

}
