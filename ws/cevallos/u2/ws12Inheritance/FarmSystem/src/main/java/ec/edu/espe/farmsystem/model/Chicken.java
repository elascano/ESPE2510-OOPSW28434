package ec.edu.espe.farmsystem.model;

import java.util.Date;


/**
 *
 * @author Mateo Cevallos Object Masters
 */
public class Chicken extends FarmAnimal{
    private boolean isMolting;
    private int laidEggs;

    @Override
    public String toString() {
        return "Chicken{" + "isMolting=" + isMolting + ", laidEggs=" + laidEggs + "," + super.toString() + '}';
    }
                

    public Chicken(boolean isMolting, int laidEggs, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
    }
    
    public int layAnEgg(){
        return laidEggs++;
    }
    
}
