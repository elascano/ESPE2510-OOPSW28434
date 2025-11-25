package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Sheep extends FarmAnimal {

    private Date lastShearing;

    @Override
    public String toString() {
        return "Sheep{" + "lastShearing=" + lastShearing + ", " + super.toString() + '}';
    }

    public Sheep(Date lastShearing, int id, String breet, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breet, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastShearing = lastShearing;
    }

    public void cutWool() {
        System.out.println("Cutting wool...");
    }

    public void shear() {
        System.out.println("Sheep sheared successfully");
    }

    /**
     * @return the lastShearing
     */
    public Date getLastShearing() {
        return lastShearing;
    }

    /**
     * @param lastShearing the lastShearing to set
     */
    public void setLastShearing(Date lastShearing) {
        this.lastShearing = lastShearing;
    }
}
