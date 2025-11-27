package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class StopButton extends Button{
    public StopButton(String name) {
        super(name);
    }

    public void toRequestStopElevator() {
        System.out.println("EMERGENCY STOP requested.");
    }
}
