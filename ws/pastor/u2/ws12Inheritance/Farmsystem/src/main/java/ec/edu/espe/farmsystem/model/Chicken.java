package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Mathews Pastor, POOwer Ranger of Programing, @ESPE
 */
public class Chicken extends FarmAnimal {
   private boolean isMolting;
   private int laidAnEggs;

    public Chicken(boolean isMolting, int laidAnEggs, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        this.isMolting = isMolting;
        this.laidAnEggs = laidAnEggs;
    }

    @Override
    public String toString() {
        return "Chicken{" + "isMolting=" + isMolting + ", laidAnEggs=" + laidAnEggs + ", " + super.toString() + '}' ;
    }
   
    
    
   public void layAnEgg(){
       laidAnEggs++;
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
     * @return the laidAnEggs
     */
    public int getLaidAnEggs() {
        return laidAnEggs;
    }

    /**
     * @param laidAnEggs the laidAnEggs to set
     */
    public void setLaidAnEggs(int laidAnEggs) {
        this.laidAnEggs = laidAnEggs;
    }
}
