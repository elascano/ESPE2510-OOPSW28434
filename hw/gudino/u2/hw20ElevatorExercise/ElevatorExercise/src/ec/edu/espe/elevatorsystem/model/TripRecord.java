package ec.edu.espe.elevatorsystem.model;

import java.time.LocalDateTime;

public class TripRecord {
    private int elevatorId;
    private int fromFloor;
    private int toFloor;
    private LocalDateTime timestamp;

    public TripRecord(int elevatorId, int fromFloor, int toFloor) {
        this.elevatorId = elevatorId;
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
        this.timestamp = LocalDateTime.now();
    }

    // getters
    public int getElevatorId() { return elevatorId; }
    public int getFromFloor() { return fromFloor; }
    public int getToFloor() { return toFloor; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

