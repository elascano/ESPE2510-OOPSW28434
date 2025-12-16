package ec.edu.espe.farmsystem.model;

import java.util.Date;

public class Chicken extends FarmAnimal{
    private boolean isMolting;
    private int laidEggs;
    
    
    public void layAnEgg(){
        laidEggs++;
    }

    public Chicken(boolean isMolting, int laidEggs, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
    }
    
    public boolean isMolting() {
        return isMolting;
    }

    public void setMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    public int getLaidEggs() {
        return laidEggs;
    }

    public void setLaidEggs(int laidEggs) {
        this.laidEggs = laidEggs;
    }
}

