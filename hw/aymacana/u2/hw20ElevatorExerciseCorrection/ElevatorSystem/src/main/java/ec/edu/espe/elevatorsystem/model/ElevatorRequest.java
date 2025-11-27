package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class ElevatorRequest {

    private String requestId;
    private int requestFromFloor;
    private int targetFloor;
    private Direction direction;
    private Person requestBy;
    private Elevator assignedElevator;

    public ElevatorRequest(String requestId, int requestFromFloor, int targetFloor, Direction direction, Person requestBy) {
        this.requestId = requestId;
        this.requestFromFloor = requestFromFloor;
        this.targetFloor = targetFloor;
        this.direction = direction;
        this.requestBy = requestBy;
    }

    public void calculatePriority() {
        System.out.println("ElevatorRequest: Calculating priority for request " + requestId);
    }

    public void assignToElevator(Elevator elevator) {
        System.out.println("ElevatorRequest: Assigning request " + requestId + " to elevator " + elevator.getElevatorId());
        this.assignedElevator = elevator;
    }

    public void markCompleted() {
        System.out.println("ElevatorRequest: Marking request " + requestId + " as completed");
    }

    public void markCancelled() {
        System.out.println("ElevatorRequest: Marking request " + requestId + " as cancelled");
    }

    public boolean matchesDirection(Direction elevatorDirection) {
        System.out.println("ElevatorRequest: Checking if request matches elevator direction");
        return true;
    }

    public String getRequestId() {
        return requestId;
    }

    public int getRequestFromFloor() {
        return requestFromFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public Person getRequestBy() {
        return requestBy;
    }

    public Elevator getAssignedElevator() {
        return assignedElevator;
    }
}
