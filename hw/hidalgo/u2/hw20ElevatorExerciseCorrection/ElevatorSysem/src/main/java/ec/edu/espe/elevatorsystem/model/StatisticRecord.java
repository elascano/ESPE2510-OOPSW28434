package ec.edu.espe.elevatorsystem.model;

import java.time.LocalDateTime;

public class StatisticRecord {
    private String person;
    private int entryFloor;
    private int exitFloor;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private int elevatorId;

    public StatisticRecord(String person, int entryFloor, int exitFloor, int elevatorId) {
        this.person = person;
        this.entryFloor = entryFloor;
        this.exitFloor = exitFloor;
        this.elevatorId = elevatorId;
        this.entryTime = LocalDateTime.now();
    }

    public void finishRide() {
        this.exitTime = LocalDateTime.now();
    }
}

