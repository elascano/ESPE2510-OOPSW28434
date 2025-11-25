package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class Sheep extends FarmAnimal {
    
    private Date lastShearing;

    public Sheep(Date lastShearing, int id, String breed, Date bornOn, String gendeR, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
        this.lastShearing = lastShearing;
    }

    @Override
    public String toString() {
        return "Sheep{" + "lastShearing=" + lastShearing + ", " + super.toString() + '}';
    }
                
    public void cutWhool(float kilogramsOfWool) {
        kilogramsOfWool++;
        
    }
    
    public void shear(String shearedSheep) {
        shearedSheep = "The sheep has been sheared";
        
    }
    
}
