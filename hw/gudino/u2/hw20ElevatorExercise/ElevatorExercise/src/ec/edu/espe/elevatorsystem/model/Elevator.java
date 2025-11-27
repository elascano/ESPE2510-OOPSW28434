package ec.edu.espe.elevatorsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private int id;
    private int direction; 
    private int passengerCount;
    private List<ShaftSensor> shaftSensors;
    private MotorSystem motor;
    private Cage cage;
    private int currentFloor;

    public Elevator(int id) {
        this.id = id;
        this.direction = 0;
        this.passengerCount = 0;
        this.shaftSensors = new ArrayList<>();
        this.motor = new MotorSystem();
        this.cage = new Cage(new ControlPanel());
        this.currentFloor = 0;
    }

    public void moveToFloor(int floor) {
        if (floor == currentFloor) {
            System.out.println("Elevator " + id + " already at floor " + floor);
            return;
        }
        if (floor > currentFloor) {
            direction = 1;
            motor.moveUp();
        } else {
            direction = -1;
            motor.moveDown();
        }
        currentFloor = floor;
        direction = 0;
        System.out.println("Elevator " + id + " moved to floor " + floor);
    }

    public void openDoors() {
        cage.openDoors();
    }

    public void closeDoors() {
        cage.closeDoors();
    }

    public boolean checkCapacity() {
        int capacity = 10; // defect
        return passengerCount <= capacity;
    }

    // getters & setters
    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public void setPassengerCount(int count) { passengerCount = count; }
    public int getPassengerCount() { return passengerCount; }
    public List<ShaftSensor> getShaftSensors() { return shaftSensors; }
    public Cage getCage() { return cage; }
}

