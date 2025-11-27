package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Josue Rojas
 */
public class Chicken extends FarmAnimal {
    private boolean isMolting;
    private int laidEggs;

    public Chicken(boolean isMolting, int laidEggs, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
        
    }

    @Override
    public String toString() {
        return "Chicken{" + "isMolting=" + isMolting + ", laidEggs=" + laidEggs + ", " + super.toString() + '}';
    }
    
    public void layAnEgg(){
        laidEggs++;
    }

    /**
     * @return the isMolting
     */
    public boolean isIsMolting() {
        return isMolting;
    }

    /**
     * @param isMolting the isMolting to set
     */
    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    /**
     * @return the laiEggs
     */
    public int getLaiEggs() {
        return laidEggs;
    }

    /**
     * @param laiEggs the laiEggs to set
     */
    public void setLaiEggs(int laiEggs) {
        this.laidEggs = laiEggs;
    }
    
}
