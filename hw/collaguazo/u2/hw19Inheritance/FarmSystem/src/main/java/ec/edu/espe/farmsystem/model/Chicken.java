/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Pablo Collaguazo
 */
import java.util.Date;

/**
 *
 */
public class Chicken extends FarmAnimal {
    private boolean isMolting;
    private int laidEggs;

   
     public void layAnEggs (){
        laidEggs++;
    }

   @Override
    public String toString() {
        return "Chicken{" +
                "\n    isMolting=" + isMolting +
                ",\n    laidEggs=" + laidEggs +
                ",\n    " + super.toString() +
                "\n}";
    }
   

    public Chicken(boolean isMoolting, int laidEggs, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMoolting;
        this.laidEggs = laidEggs;
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
