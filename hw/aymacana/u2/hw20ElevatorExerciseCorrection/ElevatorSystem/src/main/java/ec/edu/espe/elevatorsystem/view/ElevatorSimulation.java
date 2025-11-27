package ec.edu.espe.elevatorsystem.view;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
import ec.edu.espe.elevatorsystem.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ElevatorSimulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RequestScheduler scheduler = new RequestScheduler() {
            @Override
            public Elevator selectElevator(ElevatorRequest request, List<Elevator> elevators) {
                System.out.println("RequestScheduler: Selecting elevator for request to floor " + request.getTargetFloor());
                if (!elevators.isEmpty()) {
                    return elevators.get(0);
                }
                return null;
            }

            @Override
            public void prioritizeRequest(List<ElevatorRequest> pendingRequests) {
                System.out.println("RequestScheduler: Prioritizing " + pendingRequests.size() + " requests");
            }

            @Override
            public boolean canAcceptRequest(Elevator elevator, ElevatorRequest request) {
                System.out.println("RequestScheduler: Checking if elevator can accept request");
                return true;
            }
        };

        Direction upDirection = new Direction() {
            @Override
            public void up() {
                System.out.println("Direction: Moving UP");
            }

            @Override
            public void down() {
                System.out.println("Direction: Cannot move DOWN while in UP direction");
            }

            @Override
            public void stop() {
                System.out.println("Direction: Stopping UP movement");
            }
        };

        Direction downDirection = new Direction() {
            @Override
            public void up() {
                System.out.println("Direction: Cannot move UP while in DOWN direction");
            }

            @Override
            public void down() {
                System.out.println("Direction: Moving DOWN");
            }

            @Override
            public void stop() {
                System.out.println("Direction: Stopping DOWN movement");
            }
        };

        Direction idleDirection = new Direction() {
            @Override
            public void up() {
                System.out.println("Direction: Starting UP from IDLE");
            }

            @Override
            public void down() {
                System.out.println("Direction: Starting DOWN from IDLE");
            }

            @Override
            public void stop() {
                System.out.println("Direction: Already IDLE");
            }
        };

        Building building = new Building(scheduler);

        ElevatorState operatingState = new ElevatorState() {
            @Override
            public void move(int targetFloor) {
                System.out.println("ElevatorState: Moving to floor " + targetFloor);
            }

            @Override
            public void stop() {
                System.out.println("ElevatorState: Stopping elevator");
            }

            @Override
            public void openDoors() {
                System.out.println("ElevatorState: Opening doors");
            }

            @Override
            public void closeDoors() {
                System.out.println("ElevatorState: Closing doors");
            }
        };

        Elevator elevator1 = new Elevator(1, 1000.0, 8, idleDirection, operatingState);
        Elevator elevator2 = new Elevator(2, 1000.0, 8, idleDirection, operatingState);

        List<Elevator> elevators = new ArrayList<>();
        elevators.add(elevator1);
        elevators.add(elevator2);

        System.out.println("=== ELEVATOR SYSTEM SIMULATION ===");
        System.out.println("Building initialized with 10 floors and 2 elevators");

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Call elevator from floor");
            System.out.println("2. Employee request");
            System.out.println("3. Visitor request");
            System.out.println("4. Control elevator from inside");
            System.out.println("5. Show elevator status");
            System.out.println("6. Exit");
            System.out.print("Select option: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    callElevator(building, scanner);
                    break;
                case 2:
                    employeeRequest(building, scanner);
                    break;
                case 3:
                    visitorRequest(building, scanner);
                    break;
                case 4:
                    controlElevator(building, scanner);
                    break;
                case 5:
                    showElevatorStatus(elevators);
                    break;
                case 6:
                    System.out.println("Exiting simulation...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void callElevator(Building building, Scanner scanner) {
        System.out.print("Enter current floor (1-10): ");
        int currentFloor = scanner.nextInt();
        System.out.print("Enter direction (1=UP, 2=DOWN): ");
        int dir = scanner.nextInt();

        Direction direction = new Direction() {
            @Override
            public void up() {
                System.out.println("Direction: Moving UP");
            }

            @Override
            public void down() {
                System.out.println("Direction: Moving DOWN");
            }

            @Override
            public void stop() {
                System.out.println("Direction: Stopping");
            }
        };

        Floor floor = building.getFloor(currentFloor);

        if (floor != null) {
            if (dir == 1) {
                floor.pressUpButton();
            } else {
                floor.pressDownButton();
            }

            Person tempPerson = new Customer(0, 70.0f, currentFloor + 1);
            ElevatorRequest request = new ElevatorRequest("REQ" + System.currentTimeMillis(),
                    currentFloor, currentFloor + 1, direction, tempPerson);
            building.getElevatorSystem().addRequest(request);
        }
    }

    private static void employeeRequest(Building building, Scanner scanner) {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter current floor: ");
        int currentFloor = scanner.nextInt();
        System.out.print("Enter destination floor: ");
        int destinationFloor = scanner.nextInt();

        Employee employee = new Employee(id, 70.0f, destinationFloor);

        Direction direction = new Direction() {
            @Override
            public void up() {
                System.out.println("Direction: Moving UP");
            }

            @Override
            public void down() {
                System.out.println("Direction: Moving DOWN");
            }

            @Override
            public void stop() {
                System.out.println("Direction: Stopping");
            }
        };

        Floor floor = building.getFloor(currentFloor);
        if (floor != null) {
            employee.pressHallButton(floor, direction);
            ElevatorRequest request = new ElevatorRequest("EMP" + id, currentFloor, destinationFloor, direction, employee);
            building.getElevatorSystem().addRequest(request);
            building.getElevatorSystem().assignRequestToElevator(request);
        }
    }

    private static void visitorRequest(Building building, Scanner scanner) {
        System.out.print("Enter visitor ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter current floor: ");
        int currentFloor = scanner.nextInt();
        System.out.print("Enter destination floor: ");
        int destinationFloor = scanner.nextInt();

        Visitor visitor = new Visitor(id, 65.0f, destinationFloor);

        Direction direction = new Direction() {
            @Override
            public void up() {
                System.out.println("Direction: Moving UP");
            }

            @Override
            public void down() {
                System.out.println("Direction: Moving DOWN");
            }

            @Override
            public void stop() {
                System.out.println("Direction: Stopping");
            }
        };

        Floor floor = building.getFloor(currentFloor);
        if (floor != null) {
            visitor.pressHallButton(floor, direction);
            ElevatorRequest request = new ElevatorRequest("VIS" + id, currentFloor, destinationFloor, direction, visitor);
            building.getElevatorSystem().addRequest(request);
            building.getElevatorSystem().assignRequestToElevator(request);
        }
    }

    private static void controlElevator(Building building, Scanner scanner) {
        System.out.print("Enter elevator ID (1 or 2): ");
        int elevatorId = scanner.nextInt();
        System.out.print("Enter target floor (1-10): ");
        int targetFloor = scanner.nextInt();

        for (Elevator elevator : building.getElevatorSystem().getElevators()) {
            if (elevator.getElevatorId() == elevatorId) {
                elevator.moveToFloor(targetFloor);
                return;
            }
        }
        System.out.println("Elevator not found");
    }

    private static void showElevatorStatus(List<Elevator> elevators) {
        System.out.println("\n=== ELEVATOR STATUS ===");
        for (Elevator elevator : elevators) {
            System.out.println("Elevator " + elevator.getElevatorId()
                    + " - Floor: " + elevator.getCurrentFloor());
        }
    }
}
