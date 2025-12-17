/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.farmsystem.model;
import java.util.Date;

/**
 *
 * @author Pablo Collaguazo
 */

public class Sheep extends FarmAnimal{
    private Date lastSheering;

    
    public void cutWhoole(){
        
    }
    
    public void shear (){
        
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "\n    lastSheering=" + lastSheering +
                ",\n    " + super.toString() +
                "\n}";
    }

    public Sheep(Date lastSheering, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastSheering = lastSheering;
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
    