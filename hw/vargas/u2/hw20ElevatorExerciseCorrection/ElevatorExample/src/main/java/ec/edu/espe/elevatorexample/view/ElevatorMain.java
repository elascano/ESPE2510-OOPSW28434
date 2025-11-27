
package ec.edu.espe.elevatorexample.view;


import java.util.Scanner;
import ec.edu.espe.elevatorexample.model.ControlSystem;
import ec.edu.espe.elevatorexample.model.Elevator;
import ec.edu.espe.elevatorexample.model.HallButton;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class ElevatorMain {
    public static void main(String[] args) {
        System.out.println(" Elevator System Prototype Started ");

        Elevator myElevator = new Elevator(2);

        ControlSystem control = new ControlSystem(myElevator);
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                int currentFloor = myElevator.getCurrentFloor();
                
                
                System.out.println("Elevator is currently at floor " + currentFloor + ".");
                System.out.println("Where do you want to go? (Enter target floor number, or 0 to exit):");
                
                
                int targetFloor;
                try {
                    
                    targetFloor = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                    continue;
                }
                
                if (targetFloor == 0) {
                    System.out.println("\n Execution Finished. ");
                    break; 
                }
                
                
                HallButton requestButton = new HallButton(currentFloor, control);
                
                requestButton.press(targetFloor);
            }
        }
    }
}
