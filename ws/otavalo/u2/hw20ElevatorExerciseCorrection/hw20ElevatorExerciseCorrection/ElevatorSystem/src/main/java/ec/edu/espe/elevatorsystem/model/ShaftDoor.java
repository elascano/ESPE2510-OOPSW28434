package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class ShaftDoor extends Door{
    public boolean openWhenCagePresent() {
        System.out.println("ShaftDoor: Verifying if cage is present to unlock...");
        return true;
    }
}
