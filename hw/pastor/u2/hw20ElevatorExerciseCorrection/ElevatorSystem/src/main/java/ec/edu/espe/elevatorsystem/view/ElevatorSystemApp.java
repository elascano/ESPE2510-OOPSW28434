package ec.edu.espe.elevatorsystem.view;
import ec.edu.espe.elevatorsystem.controller.ElevatorControlSystem;
import ec.edu.espe.elevatorsystem.model.Elevator;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class ElevatorSystemApp {
    public static void main(String[] args) {
        System.out.println("--- Elevator System Prototype Starting by Otavalo and Pastor---");
        ElevatorControlSystem system = new ElevatorControlSystem();
        
        system.requestElevator(5);
        
        Elevator elevator = system.findBestElevator(5);
        elevator.openDoors();
        elevator.closeDoors();
        
        System.out.println("--- Prototype Execution Finished ---");
    }
}
