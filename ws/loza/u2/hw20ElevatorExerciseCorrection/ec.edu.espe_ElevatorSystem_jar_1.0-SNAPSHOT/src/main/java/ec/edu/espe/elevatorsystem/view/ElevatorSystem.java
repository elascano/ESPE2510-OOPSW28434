/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ec.edu.espe.elevatorsystem.view;

/**
 *
 * @author Steven Loza @ESPE
 */
import ec.edu.espe.elevatorsystem.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ElevatorSystem {
    private Building building;
    private ControlPanel controlPanel;
    private List<Elevator> elevators;

    
    public ElevatorSystem(Building building, List<Elevator> elevators) {
        this.building = building;
        this.elevators = elevators;
        this.controlPanel = new ControlPanel(1);
    }

    
    public static void main(String[] args) {
        Building building = new Building("Third National Bank", 20);
        ElevatorSystem system = (ElevatorSystem) building.getElevatorSystem();
        system.runConsole();
    }

    
    public void runConsole() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== WELCOME TO THE ELEVATOR SYSTEM ===");

        
        System.out.print("How many people will enter the elevator? ");
        int peopleCount = readInt(sc, 1, 100);

        List<Person> people = new ArrayList<>();
        double totalWeight = 0.0;

        for (int i = 1; i <= peopleCount; i++) {
            System.out.println("---- Person " + i + " ----");
            System.out.print("Enter name: ");
            String name = readLine(sc);
            System.out.print("Enter weight (kg): ");
            double weight = readDouble(sc);
            System.out.print("Is employee? (yes/no): ");
            String t = sc.next();
            boolean isEmployee = t.equalsIgnoreCase("yes");
            Person p = isEmployee ? new Employee(name, weight) : new Visitor(name, weight);
            people.add(p);
            totalWeight += weight;
            System.out.println(p.toString());
        }

        System.out.println("Total weight of group: " + totalWeight + " kg");

        // Choose elevator
        System.out.print("Do you want to request an elevator now? (yes/no): ");
        String want = sc.next();
        if (!want.equalsIgnoreCase("yes")) {
            System.out.println("No elevator requested. Goodbye.");
            return;
        }

        System.out.print("Select elevator (A, B, C): ");
        String choice = sc.next().toUpperCase();
        Elevator elevator = getElevatorById(choice);
        if (elevator == null) {
            System.out.println("Invalid elevator option.");
            return;
        }

        System.out.println("You selected Elevator " + elevator.getId());
        
        System.out.println(elevator.getMovementSensor().readMovementDirection());

        
        if (totalWeight > elevator.getMaxWeightKg()) {
            System.out.println("ERROR: Total weight (" + totalWeight + " kg) exceeds elevator capacity (" + elevator.getMaxWeightKg() + " kg).");
            return;
        }

        
        System.out.print("Enter current floor: ");
        int current = readInt(sc, 1, building.getFloors());
        System.out.print("Enter destination floor: ");
        int target = readInt(sc, 1, building.getFloors());

        
        if (!elevator.canAccessFloor(target)) {
            System.out.println("ACCESS DENIED: Elevator " + elevator.getId() + " cannot access floor " + target);
            return;
        }

       
        System.out.print("Press alarm now? (yes/no): ");
        String pressAlarmNow = sc.next();
        if (pressAlarmNow.equalsIgnoreCase("yes")) {
            elevator.getAlarmSensor().triggerAlarm();
            System.out.println("Alarm triggered. Elevator stopped.");
            return;
        }

        
        System.out.println("Closing doors...");
        elevator.closeDoor();

        
        System.out.println("Starting movement from floor " + current + " to " + target);
        
        elevator.getFloorSensor().setCurrentFloor(current);

      
        System.out.print("Do you want to press alarm inside during trip? (yes/no): ");
        String pressAlarmDuringTrip = sc.next();

        
        if (pressAlarmDuringTrip.equalsIgnoreCase("yes")) {
            
            System.out.print("At which floor number (during movement) will you press alarm? (enter floor number or 0 to press immediately): ");
            int alarmAtFloor = readInt(sc, 0, building.getFloors());
           
            boolean stoppedByAlarm = false;
            elevator.getMovementSensor().readMovementDirection(); 
            elevator.randomMovementMessage();

            
            int cur = current;
            if (cur < target) {
                for (int f = cur; f <= target; f++) {
                    System.out.println("Floor: " + f);
                    sleep(400);
                    if (alarmAtFloor == f) {
                        elevator.getAlarmSensor().triggerAlarm();
                        stoppedByAlarm = true;
                        break;
                    }
                }
            } else {
                for (int f = cur; f >= target; f--) {
                    System.out.println("Floor: " + f);
                    sleep(400);
                    if (alarmAtFloor == f) {
                        elevator.getAlarmSensor().triggerAlarm();
                        stoppedByAlarm = true;
                        break;
                    }
                }
            }

            if (stoppedByAlarm) {
                System.out.println("Elevator stopped due to alarm.");
                return;
            } else {
                System.out.println("✔ Arrived at floor " + target);
                return;
            }
        } else {
            
            boolean alarmed = elevator.simulateMovement(current, target);
            if (alarmed) {
                System.out.println("Elevator stopped due to alarm.");
            } else {
                System.out.println("Trip finished normally.");
            }
        }

        System.out.println("Opening doors...");
        elevator.openDoor();

        System.out.println("Thank you for using the system.");
    }

    private Elevator getElevatorById(String id) {
        for (Elevator e : elevators) {
            if (e.getId().equalsIgnoreCase(id)) return e;
        }
        return null;
    }

    private static int readInt(Scanner sc, int min, int max) {
        int v;
        while (true) {
            try {
                v = sc.nextInt();
                if (v < min || v > max) {
                    System.out.print("Invalid. Enter a number between " + min + " and " + max + ": ");
                    continue;
                }
                return v;
            } catch (Exception ex) {
                System.out.print("Invalid. Enter an integer: ");
                sc.next(); 
            }
        }
    }

    private static double readDouble(Scanner sc) {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (Exception ex) {
                System.out.print("Invalid. Enter a numeric value: ");
                sc.next();
            }
        }
    }

    private static String readLine(Scanner sc) {
        sc.nextLine(); 
        return sc.nextLine();
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ex) {}
    }
}

