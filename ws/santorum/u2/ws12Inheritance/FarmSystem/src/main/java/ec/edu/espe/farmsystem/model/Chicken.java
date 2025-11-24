package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Thais Santórum
 */
public class Chicken extends FarmAnimal {
    private boolean isMolting;
    private int laidEggs;

    public Chicken(int id, String breed, Date bornOn, String gender, boolean ableToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, ableToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
    }
    
    @Override
    public String toString() {
        return "Chicken{" + "isMolting=" + isMolting + ", laidEggs=" + laidEggs + '}';
    }



    public void layAnEgg(){
        setLaidEggs(getLaidEggs() + 1);
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
     * @return the laidEggs
     */
    public int getLaidEggs() {
        return laidEggs;
    }

    /**
     * @param laidEggs the laidEggs to set
     */
    public void setLaidEggs(int laidEggs) {
        this.laidEggs = laidEggs;
    }

}