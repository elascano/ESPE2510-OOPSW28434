package ec.edu.espe.ElevatorSystem.model;

/**
 * @author JOSUE ROJAS y THAIS SANTORUM
 */

public class ElevatorC extends Elevator {
    
    public ElevatorC() {
        super(1000); 
    }

    @Override
    public boolean isFloorAllowed(int floor) {
        // Logic: Lobby (1) OR High Floors (16-20)
        return floor == 1 || (floor >= 16 && floor <= 20);
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
        return "Elevator C (Express 16-20)";
    }
}