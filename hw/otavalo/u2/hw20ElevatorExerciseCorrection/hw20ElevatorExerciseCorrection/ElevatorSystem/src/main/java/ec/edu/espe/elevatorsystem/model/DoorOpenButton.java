package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class DoorOpenButton extends Button{
    public DoorOpenButton(String name) {
        super(name);
    }

    public void toRequestOpenDoors() {
        System.out.println("Requesting to OPEN doors manually.");
    }
}
