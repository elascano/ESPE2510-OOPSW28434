package ec.edu.espe.farmsymulator.model;

import java.util.Date;

/**
 *
 * @author Bryan Gudino
 */
public class Cow extends FarmAnimal {
    private boolean isProducingMilk;
    private float litersADay;

    public Cow(boolean isProducingMilk, float litersADay, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.litersADay = litersADay;
    }

    public float milk() {
        if (isProducingMilk) {
            return litersADay;
        } else {
            return 0.0f;
        }
    }

    @Override
    public String toString() {
        return "Cow{" + "isProducingMilk=" + isProducingMilk + ", litersADay=" + litersADay + "," + super.toString() + '}';
    }

    // Getters and Setters
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
     * @return the litersADay
     */
    public float getLitersADay() {
        return litersADay;
    }

    /**
     * @param litersADay the litersADay to set
     */
    public void setLitersADay(float litersADay) {
        this.litersADay = litersADay;
    }
}