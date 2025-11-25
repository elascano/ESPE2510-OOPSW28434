package ec.edu.espe.farmsystem.model;
import java.util.Date;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Cow extends FarmAnimal {
    private boolean isProducingMilk;
    private float littersADay;

    public Cow(boolean isProducingMilk, float littersADay, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        this.isProducingMilk = isProducingMilk;
        this.littersADay = littersADay;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               " > Is Milking    : " + (isProducingMilk ? "Yes" : "No") + "\n" +
               " > Milk/Day      : " + littersADay + " Liters\n" +
               "========================================";
    }

    public float milk() {
        return littersADay;
    }

    public boolean isIsProducingMilk() {
        return isProducingMilk;
    }

    public void setIsProducingMilk(boolean isProducingMilk) {
        this.isProducingMilk = isProducingMilk;
    }

    public float getLittersADay() {
        return littersADay;
    }

    public void setLittersADay(float littersADay) {
        this.littersADay = littersADay;
    }
}
