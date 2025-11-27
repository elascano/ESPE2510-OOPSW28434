package ec.edu.espe.elevatorsystem.model;

public class Elevator {

    private int id;
    private int currentFloor;
    private Direction direction;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void moveToFloor(int floor, Direction direction) {
        System.out.println("Elevator " + id + " moving from floor " +
                currentFloor + " to floor " + floor + " (" + direction + ")");

        this.direction = direction;
        this.currentFloor = floor;

        // When movement finishes
        this.direction = Direction.IDLE;

        System.out.println("Elevator " + id + " arrived at floor " + currentFloor);
    }
}
