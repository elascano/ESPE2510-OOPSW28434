package ec.edu.espe.elevatorsystem.model;

import java.util.ArrayList;
import java.util.List;

public class StatisticsRecord {
    private List<TripRecord> records;

    public StatisticsRecord() {
        this.records = new ArrayList<>();
    }

    public void logTrip(TripRecord record) {
        records.add(record);
        System.out.println("Trip logged: elevator " + record.getElevatorId() + " from " + record.getFromFloor() + " to " + record.getToFloor());
    }

    public List<TripRecord> getRecords() { return records; }
}
