package ec.edu.espe.farmsystem.model;

import java.util.Date;

public class Sheep extends FarmAnimal {
    private Date lastSheering;

    public Sheep(Date lastSheering, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastSheering = lastSheering;
    }

    public void cutWhool(){
        System.out.println("Cutting wool from the sheep...");
        this.shear();
    }
    
    public void shear(){
        this.lastSheering = new Date(); // Actualiza la fecha a la actual
        System.out.println("Sheep shorn. New shearing date: " + this.lastSheering);
    }

    @Override
    public String toString() {
        return "Sheep{" + "lastSheering=" + lastSheering + ", " + super.toString() + '}';
    }

    public Date getLastSheering() {
        return lastSheering;
    }

    public void setLastSheering(Date lastSheering) {
        this.lastSheering = lastSheering;
    }
}