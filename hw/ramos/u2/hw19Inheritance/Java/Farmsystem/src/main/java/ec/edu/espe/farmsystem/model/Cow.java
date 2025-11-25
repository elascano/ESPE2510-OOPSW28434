package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Paulo Ramos
 */
public class Cow extends FarmAnimal {

    private boolean isProducingMilk;
    private float milkQuantityPerDay;

    public Cow(boolean isProducingMilk, float milkQuantityPerDay, int id, String breed, Date bornOn,
            String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.milkQuantityPerDay = milkQuantityPerDay;
    }

    public float milk() {
        if (isProducingMilk) {
            return milkQuantityPerDay;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Cow{"
                + "isProducingMilk=" + isProducingMilk
                + ", milkQuantityPerDay=" + milkQuantityPerDay
                + ", " + super.toString()
                + '}';
    }

    public boolean isIsProducingMilk() {
        return isProducingMilk;
    }

    public void setIsProducingMilk(boolean isProducingMilk) {
        this.isProducingMilk = isProducingMilk;
    }

    public float getMilkQuantityPerDay() {
        return milkQuantityPerDay;
    }

    public void setMilkQuantityPerDay(float milkQuantityPerDay) {
        this.milkQuantityPerDay = milkQuantityPerDay;
    }
}
