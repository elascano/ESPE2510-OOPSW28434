package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Mateo Cevallos
 */
public class Sheep extends FarmAnimal{
    private Date lastSheering;

    @Override
    public String toString() {
        return "Sheep{" + "lastSheering=" + getLastSheering() + "," + super.toString() + '}';
    }
    
    public Sheep(Date lastSheering, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastSheering = lastSheering;
    }
    
    public void cutWhool(){
       
    }
    
    public void shear(){
        
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
