package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class SensorElevator extends Sensor{
    public boolean checkWeight() {
        System.out.println("Checking weight inside elevator...");
        return true;
    }

    public boolean checkDoors(boolean isOpen) {
        System.out.println("Checking if doors are " + (isOpen ? "open" : "closed"));
        return isOpen;
    }

    public boolean checkNumberOfPerson() {
        System.out.println("Counting people via sensors...");
        return true;
    }
}
