package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Elevator {

    private int elevatorId;
    private Cage cage;
    private int currentFloor;
    private Direction currentDirection;
    private ElevatorState currentState;
    private double currentWeight;
    private double maxWeight;
    private int maxCapacity;
    private Motor motor;
    private Sensor sensor;

    public Elevator(int elevatorId, double maxWeight, int maxCapacity, Direction direction, ElevatorState elevatorState) {
        this.elevatorId = elevatorId;
        this.cage = new Cage("Cage-" + elevatorId, this);
        this.currentFloor = 1;
        this.currentDirection = direction;
        this.currentState = elevatorState;
        this.maxWeight = maxWeight;
        this.maxCapacity = maxCapacity;
        this.motor = new Motor();
        this.sensor = new Sensor();
    }

    public void moveToFloor(int targetFloor) {
        System.out.println("Elevator " + elevatorId + " moving from floor " + currentFloor + " to floor " + targetFloor);
        currentFloor = targetFloor;
        System.out.println("Elevator " + elevatorId + " arrived at floor " + currentFloor);
    }

    public void updateSensorData(int floor, Direction dir) {
        System.out.println("Updating sensor data - Floor: " + floor + ", Direction: " + dir);
        this.currentFloor = floor;
        this.currentDirection = dir;
    }

    public boolean isCapacityExceeded() {
        System.out.println("Checking if capacity is exceeded");
        boolean exceeded = currentWeight > maxWeight;
        System.out.println("Capacity exceeded: " + exceeded);
        return exceeded;
    }

    public boolean addPassenger(Person p) {
        System.out.println("Adding passenger to elevator " + elevatorId);
        if (!isCapacityExceeded()) {
            System.out.println("Passenger added successfully");
            return true;
        } else {
            System.out.println("Cannot add passenger - capacity exceeded");
            return false;
        }
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public ElevatorState getCurrentState() {
        return currentState;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }
}
