package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */
import java.util.ArrayList;
import java.util.List;

public class Elevator {

    private int id = 1;
    private int currentFloor = 0;
    private Direction direction = Direction.IDLE;
    private boolean isMoving;
    private List<Person> passengers = new ArrayList<>();
    private double weight = 0.0;
    private double maxWeight = 600;
    private int maxPerson = 8;

    public void moveUp() {
        direction = Direction.UP;
        System.out.println("Moving upwards...");
    }

    public void moveDown() {
        direction = Direction.DOWN;
        System.out.println("Moving downwards...");
    }

    public void openDoors() {
        System.out.println("Open doors.");
    }

    public void closeDoors() {
        System.out.println("Closed doors.");
    }

    public void addRequest(int floor) {
        System.out.println("Request added: go to floor " + floor);
    }

    public void goTo(int floor) {
        System.out.println("Going up to the floor " + floor + "...");
        currentFloor = floor;
    }
}
