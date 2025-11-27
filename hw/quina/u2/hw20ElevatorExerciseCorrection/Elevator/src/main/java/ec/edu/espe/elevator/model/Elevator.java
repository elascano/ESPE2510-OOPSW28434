package ec.edu.espe.elevator.model;

/**
 *
 * @author Paulo Ramos
 */
public class Elevator {

    private int id;
    private int currentFloor;

    public Elevator(int id, int currentFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

    public void openDoors() {
        System.out.println("Doors opening...");
    }

    public void closeDoors() {
        System.out.println("Doors closing...");
    }
    
    public void moveToFloor(int destinationFloor) {
        System.out.println("Starting movement...");

        if (destinationFloor > currentFloor) {
            for (int i = currentFloor + 1; i <= destinationFloor; i++) {
                System.out.println("Passing floor " + i);
            }
        } else {
            for (int i = currentFloor - 1; i >= destinationFloor; i--) {
                System.out.println("Passing floor " + i);
            }
        }

        System.out.println("Arrived at floor " + destinationFloor);
        currentFloor = destinationFloor;
    }

}
