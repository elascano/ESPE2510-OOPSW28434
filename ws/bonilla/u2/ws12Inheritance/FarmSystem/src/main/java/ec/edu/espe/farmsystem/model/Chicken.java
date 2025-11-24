package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class Chicken extends FarmAnimal{
    private boolean isMolting;
    private int laidEgg;

    @Override
    public String toString() {
        return "Chicken{" + "isMolting = " + isMolting + ", laidEgg = " + laidEgg + super.toString() + '}';
    }
    
    
    
    public Chicken(boolean isMolting, int laidEgg, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEgg = laidEgg;
    }
    
    public void layAnEgg(){
        setLaidEgg(getLaidEgg() + 1);
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
     * @return the laidEgg
     */
    public int getLaidEgg() {
        return laidEgg;
    }

    /**
     * @param laidEgg the laidEgg to set
     */
    public void setLaidEgg(int laidEgg) {
        this.laidEgg = laidEgg;
    }
    
}
