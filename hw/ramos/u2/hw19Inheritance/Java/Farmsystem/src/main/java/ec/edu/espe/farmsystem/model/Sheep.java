package ec.edu.espe.farmsystem.model;

import java.util.Date;
/**
 *
 * @author Paulo Ramos
 */


public class Sheep extends FarmAnimal {
    private Date lastSheering;

    public Sheep(Date lastSheering, int id, String breed, Date bornOn, String gender,
                 boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastSheering = lastSheering;
    }

    public void shear() {
        lastSheering = new Date(); 
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "lastSheering=" + lastSheering +
                ", " + super.toString() +
                '}';
    }

    public Date getLastSheering() {
        return lastSheering;
    }

    public void setLastSheering(Date lastSheering) {
        this.lastSheering = lastSheering;
    }
}
