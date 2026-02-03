package ec.edu.espe.strategy.controller;

import ec.edu.espe.strategy.model.Parking;
import ec.edu.espe.strategy.utils.Persistence;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Arelis Samantha Bonilla Cruz
 */
public class ParkingController {

    private Persistence strategy;
    private static final double RATE_PER_HOUR = 2.5;

    public ParkingController() {
        this.strategy = null;
    }

    public ParkingController(Persistence strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Persistence strategy) {
        this.strategy = strategy;
    }

    public double calculateFee(LocalDateTime entry, LocalDateTime exit) {
        long hours = Duration.between(entry, exit).toHours();
        if (hours == 0) hours = 1;
        return hours * RATE_PER_HOUR;
    }

    public boolean registerEntry(String id, String plate, String vehicleType, LocalDateTime entryTime) {
        if(strategy == null || id.isEmpty() || plate.isEmpty() || vehicleType.isEmpty() || entryTime == null) return false;
        Parking parking = new Parking(id, plate, vehicleType, entryTime, null, 0.0);
        return strategy.create(parking);
    }

    public boolean registerExit(String id) {
        if(strategy == null || id.isEmpty()) return false;
        Parking parking = strategy.find(id);
        if (parking != null) {
            LocalDateTime exitTime = LocalDateTime.now();
            double fee = calculateFee(parking.getEntryTime(), exitTime);
            parking.setExitTime(exitTime);
            parking.setFee(fee);
            return strategy.update(id, parking);
        }
        return false;
    }

    public List<Parking> getAllParkings() {
        if(strategy == null) return List.of();
        return strategy.read();
    }

    public Parking findById(String id) {
        if(strategy == null || id.isEmpty()) return null;
        return strategy.find(id);
    }

    public boolean delete(String id) {
        if(strategy == null || id.isEmpty()) return false;
        return strategy.delete(id);
    }

    public String getNextId() {
        List<Parking> parkings = getAllParkings();
        if (parkings.isEmpty()) return "1";
        return String.valueOf(
            parkings.stream()
                    .map(Parking::getId)
                    .mapToInt(Integer::parseInt)
                    .max()
                    .getAsInt() + 1
        );
    }
}