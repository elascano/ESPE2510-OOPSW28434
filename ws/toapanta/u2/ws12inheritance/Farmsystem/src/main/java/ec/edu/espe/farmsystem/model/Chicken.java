
package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class Chicken extends FarmAnimal{
    boolean isMolting;
    int laidEggs;

    public Chicken(boolean isMolting, int laidEggs, int par, String breed1, Date bornOn1, boolean abelToReproduce, float weigth1, Location location, String gender1, int id1, Cage cage1) {
        super(par, breed1, bornOn1, abelToReproduce, weigth1, location, gender1, id1, cage1);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
    }

    public void layAnEgg(){
        laidEggs++;
    }

    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    public void setLaidEggs(int laidEggs) {
        this.laidEggs = laidEggs;
    }

    public boolean isIsMolting() {
        return isMolting;
    }

    public int getLaidEggs() {
        return laidEggs;
    }

}
