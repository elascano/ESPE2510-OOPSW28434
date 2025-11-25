package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Joseph Medina Espe
 */
public class Chicken extends FarmAnimal {
    private boolean isMolting;
    private int laidEggs;

    public Chicken(boolean isMolting, int laidEggs, int id, String breed, Date bornOn, String gendeR, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;      
    }

    @Override
    public String toString() {
        return "Chicken{" + "isMolting=" + isMolting + ", laidEggs=" + laidEggs + "," + super.toString() + '}';
        
    }
        
    public void layAnEgg(){
        laidEggs++;
    }
    
}
