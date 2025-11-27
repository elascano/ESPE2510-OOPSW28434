package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {

    private List<Elevator> elevators;
    private RequestScheduler scheduler;

    public ElevatorSystem(RequestScheduler scheduler) {
        this.elevators = new ArrayList<>();
        this.scheduler = scheduler;
    }

    public void addRequest(ElevatorRequest request) {
        System.out.println("ElevatorSystem: Adding request from floor " + request.getRequestFromFloor() + " to floor " + request.getTargetFloor());
    }

    public void assignRequestToElevator(ElevatorRequest request) {
        System.out.println("ElevatorSystem: Assigning request to elevator");
    }

    public void addElevator(Elevator elevator) {
        System.out.println("ElevatorSystem: Adding elevator " + elevator.getElevatorId() + " to system");
        elevators.add(elevator);
    }

    public List<Elevator> getElevators() {
        return elevators;
    }
}
