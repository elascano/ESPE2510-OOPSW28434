package ec.edu.espe.elevatorsystem.controller;
import ec.edu.espe.elevatorsystem.model.Elevator;
import ec.edu.espe.elevatorsystem.model.Button;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class ControlPanel {
    private Elevator elevator;

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public void openDoor() {
        System.out.println("Control Panel: Command to Open Door.");
    }

    public void closeDoor() {
        System.out.println("Control Panel: Command to Close Door.");
    }

    public boolean turnOnAlarm() {
        System.out.println("Control Panel: Alarm Signal Sent.");
        return true;
    }

    public boolean stopEmergencyElevator() {
        System.out.println("Control Panel: Emergency Stop Triggered.");
        return true;
    }

    public void selectFloor(int floorNumber) {
        System.out.println("Control Panel: Floor " + floorNumber + " selected.");
    }
    
    public void selectButton(int option) {
         System.out.println("Control Panel: Button " + option + " pressed.");
    }
}
