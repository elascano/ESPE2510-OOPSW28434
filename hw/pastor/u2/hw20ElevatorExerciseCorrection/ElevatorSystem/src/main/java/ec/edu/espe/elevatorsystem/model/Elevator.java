package ec.edu.espe.elevatorsystem.model;
import java.util.ArrayList;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Elevator {
    private int id;
    private int currentFloor;
    private String state; // Using String for ElevatorState as per simple types
    private float weight;
    private boolean door;
    private float currentWeight;
    private float MAX_WEIGHT = 4000.0F;
    private int MAX_PERSONS = 20;
    private boolean alarm;
    private ArrayList<Person> persons;
    private ArrayList<Button> buttons;
    
    // Composition References
    private Motor motor;
    private Break elevatorBreak;
    private Light light;
    private Cage cage;

    public void stop() {
        System.out.println("Elevator " + id + " has stopped.");
    }

    public void openDoors() {
        System.out.println("Elevator doors opening.");
        this.door = true;
    }

    public void closeDoors() {
        System.out.println("Elevator doors closing.");
        this.door = false;
    }

    public boolean checkWeight() {
        System.out.println("Checking weight limit.");
        return currentWeight <= MAX_WEIGHT;
    }

    public void calculatePossiblePosition() {
        System.out.println("Calculating next position based on algorithm.");
    }

    public void checkStatus(ArrayList<Person> people) {
        System.out.println("Checking elevator status with passengers.");
    }
}
