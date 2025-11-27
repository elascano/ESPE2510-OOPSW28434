package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class CageDoor extends Door{
    public boolean detectedObstruction() {
        System.out.println("CageDoor: Sensors checking for obstructions...");
        return false;
    }
}
