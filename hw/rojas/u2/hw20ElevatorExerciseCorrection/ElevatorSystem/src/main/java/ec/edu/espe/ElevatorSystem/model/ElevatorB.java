package ec.edu.espe.ElevatorSystem.model;

/**
 * @author JOSUE ROJAS y THAIS SANTORUM
 */

public class ElevatorB extends Elevator {
    
    public ElevatorB() {
        super(800); 
    }

    @Override
    public boolean isFloorAllowed(int floor) {
        // Logic: Floors 1 to 15
        return floor >= 1 && floor <= 15;
    }

    @Override
    public int getMaxFloor() {
        return 15;
    }

    @Override
    public int getMinFloor() {
        return 1;
    }
    
    @Override
    public String toString() {
        return "Elevator B (Floors 1-15)";
    }
}