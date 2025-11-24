package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Mathews Pastor, Poower Rangers of Programing, @ESPE
 */
public class Chicken extends FarmAnimal {
    private boolean isMoolting;
    private int laidEggs;

    @Override
    public String toString() {
        return "Chicken{" + "isMoolting=" + isMoolting + ", laidEggs=" + laidEggs + super.toString()+'}';
    }

    
    public Chicken(boolean isMoolting, int laidEggs, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    }
          
    public void layAnEggs (){
        laidEggs++;
    }
    
    /**
     * @return the isMoolting
     */
    public boolean isIsMoolting() {
        return isMoolting;
    }

    /**
     * @param isMoolting the isMoolting to set
     */
    public void setIsMoolting(boolean isMoolting) {
        this.isMoolting = isMoolting;
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
