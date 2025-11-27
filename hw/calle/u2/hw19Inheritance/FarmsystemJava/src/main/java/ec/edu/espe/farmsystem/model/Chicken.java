package ec.edu.espe.farmsystem.model;

import java.util.Date;

public class Chicken extends FarmAnimal {
    private boolean isMolting;
    private int laidEggs;

    public Chicken(boolean isMolting, int laidEggs, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
    }

    public void layAnEgg(){
        setLaidEggs(getLaidEggs() + 1);
    }

    @Override
    public String toString() {
        return "Chicken{" + "isMolting=" + isMolting + ", laidEggs=" + laidEggs + ", " + super.toString() + '}';
    }

    public boolean isIsMolting() {
        return isMolting;
    }

    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    public int getLaidEggs() {
        return laidEggs;
    }

    public void setLaidEggs(int laidEggs) {
        this.laidEggs = laidEggs;
    }
}