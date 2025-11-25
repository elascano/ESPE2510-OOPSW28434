
package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class Cow extends FarmAnimal{
    private boolean isProducingMilk;
    private float littersADay;
    
    public float milk(){
       return 0; 
    }

    @Override
    public String toString() {
        return "Cow{" +
                "\n    isProducingMilk=" + isProducingMilk +
                ",\n    littersADay=" + littersADay +
                ",\n    " + super.toString() +
                "\n}";
    }

    public Cow(boolean isProducingMilk, float littersADay, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.littersADay = littersADay;
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
        return littersADay;
    }

    /**
     * @param littersADay the littersADay to set
     */
    public void setLittersADay(float littersADay) {
        this.littersADay = littersADay;
    }
    
}
