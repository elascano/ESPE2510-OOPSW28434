package ec.edu.espe.farmsymulator.model;

import java.util.Date;

public class Sheep extends FarmAnimal {
    private Date lastShearing;

    public Sheep(Date lastShearing, int id, String breed, Date bornOn, String gender, 
                 boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastShearing = lastShearing;
    }

    @Override
    public String toString() {
        return "Sheep{" + "lastShearing=" + lastShearing + 
                ", " + super.toString() + '}';
    }

    public void cutWool() {
        lastShearing = new Date();
    }

    public int shear() {
        Date today = new Date();
        long diff = today.getTime() - lastShearing.getTime();
        int days = (int) (diff / (1000L * 60 * 60 * 24));
        return days;
    }

    public Date getLastShearing() {
        return lastShearing;
    }

    public void setLastShearing(Date lastShearing) {
        this.lastShearing = lastShearing;
    }
    
}