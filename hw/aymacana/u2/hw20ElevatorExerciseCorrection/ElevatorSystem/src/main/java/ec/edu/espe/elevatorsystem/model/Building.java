package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
import java.util.ArrayList;
import java.util.List;

public class Building {

    private List<Floor> floors;
    private ElevatorSystem elevatorSystem;

    public Building(RequestScheduler scheduler) {
        this.floors = new ArrayList<>();
        this.elevatorSystem = new ElevatorSystem(scheduler);
    }

    public Floor getFloor(int floorNumber) {
        System.out.println("Getting floor " + floorNumber + " from building");
        for (Floor floor : floors) {
            if (floor.getFloorNumber() == floorNumber) {
                return floor;
            }
        }
        return null;
    }

    public ElevatorSystem getElevatorSystem() {
        return elevatorSystem;
    }
}
