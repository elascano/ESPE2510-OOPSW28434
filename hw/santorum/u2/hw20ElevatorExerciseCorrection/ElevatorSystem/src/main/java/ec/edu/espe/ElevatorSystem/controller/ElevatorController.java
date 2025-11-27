package ec.edu.espe.ElevatorSystem.controller;

import ec.edu.espe.ElevatorSystem.model.*; 
import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private List<Elevator> elevators;

    public ElevatorController() {
        this.elevators = new ArrayList<>();
        elevators.add(new ElevatorA());
        elevators.add(new ElevatorB());
        elevators.add(new ElevatorC());
        elevators.add(new ElevatorD());
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public String processMoveRequest(Elevator elevator, int targetFloor, boolean wantsToGoUp) {
        elevator.getCage().getControlPanel().closeDoor();

        if (!elevator.isFloorAllowed(targetFloor)) {
            return "ERROR: " + elevator.toString() + " cannot reach floor " + targetFloor + ".";
        }

        int current = elevator.getCurrentFloor();

        if (current == targetFloor) return "WARNING: You are already on floor " + targetFloor + ".";
        
        if (wantsToGoUp && targetFloor < current) {
            return "Logic Error: You chose UP but the target floor is lower.";
        }
        if (!wantsToGoUp && targetFloor > current) {
            return "Logic Error: You chose DOWN but the target floor is higher.";
        }

 
        elevator.getCage().getControlPanel().openTheDoor();

        return "SUCCESS: Arrived at destination: Floor " + targetFloor;
    }
}   