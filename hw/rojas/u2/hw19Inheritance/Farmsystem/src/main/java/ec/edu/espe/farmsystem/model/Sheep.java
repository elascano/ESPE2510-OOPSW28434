/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Josue Rojas
 */
public class Sheep extends FarmAnimal {
    private Date lastShearing;

    @Override
    public String toString() {
        return "Sheep{" + "lastShearing=" + lastShearing + '}'+ super.toString();
    }
    
    
    
    public Sheep(Date lastShearing, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastShearing = lastShearing;
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