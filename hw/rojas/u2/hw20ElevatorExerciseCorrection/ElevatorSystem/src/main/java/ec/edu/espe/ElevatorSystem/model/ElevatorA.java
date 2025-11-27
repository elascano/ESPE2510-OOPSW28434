package ec.edu.espe.ElevatorSystem.model;

/**
 * @author JOSUE ROJAS y THAIS SANTORUM
 */

public class ElevatorA extends Elevator {
    public ElevatorA() { super(600); }

    @Override
    public boolean isFloorAllowed(int floor) {
        return floor >= 1 && floor <= 10;
    }

    @Override
    public int getMaxFloor() { return 10; } 

    @Override
    public int getMinFloor() { return 1; }
    
    @Override
    public String toString() { return "Elevator A (Floors 1-10)"; }
}