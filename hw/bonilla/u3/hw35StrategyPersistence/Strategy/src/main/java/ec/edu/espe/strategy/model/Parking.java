package ec.edu.espe.strategy.model;

import java.time.LocalDateTime;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class Parking {

    private String id;
    private String plate;
    private String vehicleType;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fee;

    public Parking(String id, String plate, String vehicleType, LocalDateTime entryTime, LocalDateTime exitTime, double fee) {
        this.id = id;
        this.plate = plate;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.fee = fee;
    }

    public String getId() { 
        return id; 
    }
    public void setId(String id) { 
        this.id = id; 
    }

    public String getPlate() { 
        return plate; 
    }
    public void setPlate(String plate) { 
        this.plate = plate; 
    }

    public String getVehicleType() { 
        return vehicleType; 
    }
    public void setVehicleType(String vehicleType) { 
        this.vehicleType = vehicleType; 
    }

    public LocalDateTime getEntryTime() { 
        return entryTime; 
    }
    public void setEntryTime(LocalDateTime entryTime) { 
        this.entryTime = entryTime; 
    }

    public LocalDateTime getExitTime() { 
        return exitTime; 
    }
    public void setExitTime(LocalDateTime exitTime) { 
        this.exitTime = exitTime; 
    }

    public double getFee() { 
        return fee; 
    }
    public void setFee(double fee) { 
        this.fee = fee; 
    }

    @Override
    public String toString() {
        return plate + " (" + vehicleType + ")";
    }
}