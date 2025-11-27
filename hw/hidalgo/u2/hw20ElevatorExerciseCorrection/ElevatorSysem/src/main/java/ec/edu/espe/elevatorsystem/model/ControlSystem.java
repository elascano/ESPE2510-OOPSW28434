package ec.edu.espe.elevatorsystem.model;

import java.util.ArrayList;
import java.util.List;

public class ControlSystem {

    private List<Elevator> elevators;

    public ControlSystem(int numberOfElevators) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }
    }

    public Elevator selectElevator() {
        // Simple selection logic: return the first elevator
        return elevators.get(0);
    }

    public Elevator getElevator(int id) {
        return elevators.get(id);
    }

    // New method to order an elevator to go somewhere
    public void sendElevatorToFloor(int elevatorId, int targetFloor) {
        Elevator elevator = elevators.get(elevatorId);

        Direction direction =
                targetFloor > elevator.getCurrentFloor() ? Direction.UP : Direction.DOWN;

        elevator.moveToFloor(targetFloor, direction);
    }
}
