package ec.edu.espe.elevator.view;

import ec.edu.espe.elevator.model.Elevator;
import ec.edu.espe.elevator.model.ElevatorControlSystem;
import java.util.Scanner;

/**
 *
 * @author Paulo Ramos
 */

public class ElevatorSystem {
public static void main(String[] args) {
        
        System.out.println("==== Elevator Bank ====");

        Scanner sc = new Scanner(System.in);
        ElevatorControlSystem system = new ElevatorControlSystem();

        System.out.print("Enter current floor of the elevator: ");
        int currentFloor = sc.nextInt();

        System.out.print("Enter destination floor: ");
        int destinationFloor = sc.nextInt();

        Elevator elevator = new Elevator(1, currentFloor);

        elevator.closeDoors();

        if (destinationFloor > currentFloor) {
            System.out.println("Elevator moving UP...");
        } else if (destinationFloor < currentFloor) {
            System.out.println("Elevator moving DOWN...");
        } else {
            System.out.println("Already at the selected floor!");
        }

        elevator.moveToFloor(destinationFloor);

        elevator.openDoors();

        System.out.println("==== Finished ====");
    }
}

