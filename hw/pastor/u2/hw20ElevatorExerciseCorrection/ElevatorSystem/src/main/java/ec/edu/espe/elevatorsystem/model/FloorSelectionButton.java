package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class FloorSelectionButton extends Button{
    public FloorSelectionButton(String name) {
        super(name);
    }

    public void toRequestSelectFloor(int numberFloor) {
        System.out.println("Floor " + numberFloor + " selected.");
    }
}
