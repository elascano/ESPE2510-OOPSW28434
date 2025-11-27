package ec.edu.espe.ElevatorSystem.model;

/**
 * @author JOSUE ROJAS y THAIS SANTORUM
 */

public abstract class Elevator {
    protected int currentFloor;
    protected int passengerCount;
    protected float weightSupported;
    
    protected Cage cage;
    protected Sensor sensor;

    public Elevator(float weightSupported) {
        this.weightSupported = weightSupported;
        this.currentFloor = 1; 
        this.passengerCount = 0;
        this.sensor = new Sensor(0, 1);
        this.cage = new Cage(); 
    }

    public abstract boolean isFloorAllowed(int floor);
    public abstract int getMaxFloor();
    public abstract int getMinFloor();

    public int getCurrentFloor() { return currentFloor; }
    
    public void setCurrentFloor(int targetFloor) { 
        sensor.showDirection(0, targetFloor);
        sensor.showSpeed(2.5f); 
        this.currentFloor = targetFloor; 
    }
    
    public int getPassengerCount() { return passengerCount; }
    public void setPassengerCount(int count) { this.passengerCount = count; }
    public Sensor getSensor() { return sensor; }
    public Cage getCage() { return cage; }
}