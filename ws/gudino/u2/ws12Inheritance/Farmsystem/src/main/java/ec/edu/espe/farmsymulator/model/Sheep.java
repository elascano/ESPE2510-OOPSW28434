package ec.edu.espe.farmsymulator.model;

import java.util.Date;

/**
 *
 * @author Bryan Gudino
 */
public class Sheep extends FarmAnimal {
    private Date lastSheering;

    public Sheep(Date lastSheering, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastSheering = lastSheering;
    }

    @Override
    public String toString() {
        return "Sheep{" + "lastSheering=" + lastSheering + "," + super.toString() + '}';
    }

    public void shear() {
        this.lastSheering = new Date();
    }
    
    public void cutWhool() {
        System.out.println("Cutting the wool of the sheep...");
    }

    /**
     * @return the lastSheering
     */
    public Date getLastSheering() {
        return lastSheering;
    }

    /**
     * @param lastSheering the lastSheering to set
     */
    public void setLastSheering(Date lastSheering) {
        this.lastSheering = lastSheering;
    }
}