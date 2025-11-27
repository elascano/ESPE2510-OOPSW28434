package ec.edu.espe.elevatorsystem.view;

import ec.edu.espe.elevatorsystem.model.ControlSystem;
import ec.edu.espe.elevatorsystem.model.Direction;
import ec.edu.espe.elevatorsystem.model.Elevator;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Elevator System Simulation ===");

        // Create a control system with 3 elevators
        ControlSystem system = new ControlSystem(3);

        // Select the first elevator (simple logic)
        Elevator selectedElevator = system.selectElevator();
        System.out.println("\nSelected Elevator: " + selectedElevator.getId());

        // Move elevator to floor 5
        System.out.println("\nRequest: Move elevator 0 to floor 5");
        system.sendElevatorToFloor(0, 5);

        // Move the same elevator to floor 2
        System.out.println("\nRequest: Move elevator 0 to floor 2");
        system.sendElevatorToFloor(0, 2);

        // Move another elevator
        System.out.println("\nRequest: Move elevator 1 to floor 7");
        system.sendElevatorToFloor(1, 7);

        System.out.println("\n=== Simulation Finished ===");
    }
}
