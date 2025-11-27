package ec.edu.espe.elevator.model;

/**
 *
 * @author Paulo Ramos
 */

public class ElevatorControlSystem {

    private Elevator elevator;

    public ElevatorControlSystem() {
        elevator = new Elevator(1, 1);  
    }

    public void requestElevator(int floor) {
        System.out.println("Requesting elevator to floor: " + floor);
    }

    public Elevator findBestElevator(int floor) {
        System.out.println("Selecting the elevator for floor " + floor);
        System.out.println("Elevator " + elevator.getId() + " selected.");
        elevator.setCurrentFloor(floor);
        return elevator;
    }
}

