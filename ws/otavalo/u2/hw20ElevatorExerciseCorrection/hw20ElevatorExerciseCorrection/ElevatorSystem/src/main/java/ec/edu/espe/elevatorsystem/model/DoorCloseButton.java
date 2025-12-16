package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class DoorCloseButton extends Button{
    public DoorCloseButton(String name) {
        super(name);
    }

    public void toRequestCloseDoors() {
        System.out.println("Requesting to CLOSE doors manually.");
    }
}
