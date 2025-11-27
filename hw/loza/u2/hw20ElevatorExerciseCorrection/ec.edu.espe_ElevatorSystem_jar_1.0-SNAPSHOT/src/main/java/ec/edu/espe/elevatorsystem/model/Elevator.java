/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
import java.util.ArrayList;
import java.util.List;

public abstract class Elevator {
    protected String id;
    protected int minFloor;
    protected int maxFloor;
    protected double maxWeightKg;

    protected Motor motor;                 // use
    protected Door door;                   // composition
    protected List<Sensor> sensors = new ArrayList<>(); // aggregation
    protected CabinPanel cabinPanel;       // composition
    protected LoadSensor loadSensor;
    protected MovementSensor movementSensor;
    protected AlarmSensor alarmSensor;
    protected FloorSensor floorSensor;
    protected DoorSensor doorSensor;

    public Elevator(String id, int minFloor, int maxFloor, double maxWeightKg) {
        this.id = id;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.maxWeightKg = maxWeightKg;

        this.motor = new Motor(100);
        this.door = new Door();

        this.alarmSensor = new AlarmSensor(100 + id.hashCode() % 100);
        this.movementSensor = new MovementSensor(200 + id.hashCode() % 100);
        this.loadSensor = new LoadSensor(300 + id.hashCode() % 100);
        this.floorSensor = new FloorSensor(400 + id.hashCode() % 100);
        this.doorSensor = new DoorSensor(500 + id.hashCode() % 100);

        // aggregate sensors
        sensors.add(alarmSensor);
        sensors.add(movementSensor);
        sensors.add(loadSensor);
        sensors.add(floorSensor);
        sensors.add(doorSensor);

        this.cabinPanel = new CabinPanel(10 + id.hashCode() % 100, alarmSensor, maxFloor);
    }

    public String getId() { return id; }
    public boolean canAccessFloor(int floor) { return floor >= minFloor && floor <= maxFloor; }
    public double getMaxWeightKg() { return maxWeightKg; }

    public MovementSensor getMovementSensor() { return movementSensor; }
    public AlarmSensor getAlarmSensor() { return alarmSensor; }
    public LoadSensor getLoadSensor() { return loadSensor; }
    public FloorSensor getFloorSensor() { return floorSensor; }
    public Door getDoor() { return door; }
    public CabinPanel getCabinPanel() { return cabinPanel; }

    public void openDoor() {
        door.open();
        doorSensor.setDoorClosed(false);
    }

    public void closeDoor() {
        door.close();
        doorSensor.setDoorClosed(true);
    }

    public void randomMovementMessage() {
        System.out.println(movementSensor.readMovementDirection());
    }

    // Simulate movement floor-by-floor. Returns if alarm triggered (stopped)
    public boolean simulateMovement(int currentFloor, int targetFloor) {
        motor.start();
        boolean alarmTriggered = alarmSensor.read();

        if (alarmTriggered) {
            motor.stop();
            return true;
        }

        if (currentFloor == targetFloor) {
            System.out.println("You are already at floor " + targetFloor);
            motor.stop();
            return alarmSensor.read();
        }

        if (currentFloor < targetFloor) {
            for (int f = currentFloor; f <= targetFloor; f++) {
                if (alarmSensor.read()) {
                    System.out.println("Alarm detected while moving. Stopping at floor " + f);
                    motor.stop();
                    return true;
                }
                System.out.println("Floor: " + f);
                sleep(400);
                floorSensor.setCurrentFloor(f);
            }
        } else {
            for (int f = currentFloor; f >= targetFloor; f--) {
                if (alarmSensor.read()) {
                    System.out.println("Alarm detected while moving. Stopping at floor " + f);
                    motor.stop();
                    return true;
                }
                System.out.println("Floor: " + f);
                sleep(400);
                floorSensor.setCurrentFloor(f);
            }
        }

        motor.stop();
        System.out.println("✔ Arrived at floor " + targetFloor);
        return alarmSensor.read();
    }

    protected void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ex) {}
    }
}
