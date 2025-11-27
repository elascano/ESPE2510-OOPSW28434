package ec.edu.espe.ElevatorSystem.model;

/**
 * @author JOSUE ROJAS y THAIS SANTORUM
 */

public class ElevatorD extends Elevator {
    
    public ElevatorD() {
        super(1000);
    }

    @Override
    public boolean isFloorAllowed(int floor) {
        return floor >= 1 && floor <= 20;
    }

    @Override
    public int getMaxFloor() {
        return 20;
    }

    @Override
    public int getMinFloor() {
        return 1;
    }
    
    @Override
    public String toString() {
        return "Elevator D (All Floors)";
    }
}