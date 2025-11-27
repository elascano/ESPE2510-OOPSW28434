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
        System.out.println("Moviendose hacia arriba...");
    }

    public void moveDown() {
        direction = Direction.DOWN;
        System.out.println("Moviendose hacia abajo...");
    }

    public void openDoors() {
        System.out.println("Puertas abiertas.");
    }

    public void closeDoors() {
        System.out.println("Puertas cerradas.");
    }

    public void addRequest(int floor) {
        System.out.println("Solicitud agregada: ir al piso " + floor);
    }

    public void goTo(int floor) {
        System.out.println("Subiendo al piso " + floor + "...");
        currentFloor = floor;
    }
}
