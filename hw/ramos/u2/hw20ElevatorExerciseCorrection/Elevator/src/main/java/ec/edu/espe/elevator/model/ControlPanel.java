package ec.edu.espe.elevator.model;

/**
 *
 * @author Maryuri Quiña
 */
public class ControlPanel {

    Button button;

    public ControlPanel(Button button) {
        this.button = button;
    }

    public void callElevator() {
        System.out.println("The elevator is going.");
    }

    public void pressAlarm() {
        System.out.println("alarm activated");
    }

    public void selectFloor() {
        System.out.println("selected floor");
    }
}
