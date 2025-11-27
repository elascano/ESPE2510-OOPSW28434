package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Mateo Cevallos
 */
public class Cow extends FarmAnimal{
    private boolean isProducingMilk;
    private float litersADay;
    
    public float milk(){
        return litersADay++;
    }

    @Override
    public String toString() {
        return "Cow{" + "isProducingMilk=" + isProducingMilk + ", littersADay=" + litersADay + "," + super.toString() + '}';
    }
    
    public Cow(boolean isProducingMilk, float littersADay, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.litersADay = littersADay;
    }
        
    /**
     * @return the isProducingMilk
     */
    public boolean isIsProducingMilk() {
        return isProducingMilk;
    }

    /**
     * @param isProducingMilk the isProducingMilk to set
     */
    public void setIsProducingMilk(boolean isProducingMilk) {
        this.isProducingMilk = isProducingMilk;
    }

    /**
     * @return the littersADay
     */
    public float getLittersADay() {
        return litersADay;
    }

    /**
     * @param littersADay the littersADay to set
     */
    public void setLittersADay(float littersADay) {
        this.litersADay = littersADay;
    }
    
    
}
