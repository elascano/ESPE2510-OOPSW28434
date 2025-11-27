package ec.edu.espe.ElevatorSystem.view;

/**
 * @author JOSUE ROJAS y THAIS SANTORUM
 */

import ec.edu.espe.ElevatorSystem.controller.ElevatorController;
import ec.edu.espe.ElevatorSystem.model.Elevator;
import java.util.Scanner;

public class ElevatorSystem {

    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n----------------------------------------");
            System.out.println("      ESPE ELEVATOR SYSTEM");
            System.out.println("----------------------------------------");
            System.out.println("1. Select an Elevator");
            System.out.println("2. Exit Building");
            System.out.print("Choose an option: ");
            
            int option = readInt(scanner);

            if (option == 2) {
                running = false;
                System.out.println("Exiting... Have a nice day!");
            } else if (option == 1) {
                System.out.println("\n--- Available Elevators ---");
                int index = 0;
                for (Elevator e : controller.getElevators()) {
                    System.out.println((index + 1) + ". " + e.toString());
                    index++;
                }
                System.out.print("Select elevator number: ");
                int choice = readInt(scanner) - 1;

                if (choice >= 0 && choice < controller.getElevators().size()) {
                    Elevator selected = controller.getElevators().get(choice);
                    enterElevatorLoop(controller, selected, scanner);
                } else {
                    System.out.println("Invalid selection.");
                }
            } else {
                System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void enterElevatorLoop(ElevatorController controller, Elevator elevator, Scanner scanner) {
        boolean inside = true;
        System.out.println("\n----------------------------------------");
        System.out.println("WELCOME TO " + elevator.toString().toUpperCase());
        System.out.println("----------------------------------------");

        while (inside) {
            int current = elevator.getCurrentFloor();
            int max = elevator.getMaxFloor();
            int min = elevator.getMinFloor();

            System.out.println("\nCurrent Floor: " + current);
            System.out.println("1. Go to a floor");
            System.out.println("2. Trigger Alarm");
            System.out.println("3. Exit elevator");
            System.out.print("Option: ");
            
            int action = readInt(scanner);

            if (action == 3) {
                inside = false;
                System.out.println("Returning to Lobby.");
            } else if (action == 2) {
                elevator.getCage().getControlPanel().triggerTheAlarm();
            } else if (action == 1) {
                
                boolean wantsUp = false;
                boolean validDirection = false;

                // --- SMART VALIDATION LOGIC ---
                if (current == max) {
                    System.out.println("\n[INFO] You are at the top floor (" + max + "). Only DOWN is allowed.");
                    System.out.println("Automatically selecting DOWN.");
                    wantsUp = false;
                    validDirection = true;
                } else if (current == min) {
                    System.out.println("\n[INFO] You are at the lowest floor (" + min + "). Only UP is allowed.");
                    System.out.println("Automatically selecting UP.");
                    wantsUp = true;
                    validDirection = true;
                } else {
                    // Intermediate floor: User can choose
                    System.out.println("\nDo you want to go UP or DOWN?");
                    System.out.println("1. UP");
                    System.out.println("2. DOWN");
                    System.out.print("Selection: ");
                    int dirOpt = readInt(scanner);
                    if(dirOpt == 1) { wantsUp = true; validDirection = true; }
                    else if(dirOpt == 2) { wantsUp = false; validDirection = true; }
                    else { System.out.println("Invalid direction."); }
                }

                if (validDirection) {
                    System.out.print("Enter target floor number: ");
                    int targetFloor = readInt(scanner);
                    String result = controller.processMoveRequest(elevator, targetFloor, wantsUp);
                    System.out.println("\n" + result);
                }
            }
        }
    }

    private static int readInt(Scanner scanner) {
        try { return Integer.parseInt(scanner.next()); } 
        catch (NumberFormatException e) { return -1; }
    }
}