package ec.edu.espe.elevatorsystem.controller;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */
import ec.edu.espe.elevatorsystem.model.Elevator;

public class ControlSystem {

    private Elevator elevator = new Elevator();

    public void assignElevator(int floor) {
        System.out.println("Assigning elevator...");
        elevator.addRequest(floor);
        elevator.moveUp();
        elevator.goTo(floor);
        elevator.openDoors();
    }

    public void updateElevator() {
        System.out.println("Updating elevator status...");
    }

    public void recordStatistics() {
        System.out.println("Recording statistics...");
    }
}

