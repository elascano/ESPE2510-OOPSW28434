
package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Windows
 */

public class Pig extends FarmAnimal {
    private float foodPerDay;
    private boolean isReadyForSale;

    public Pig(float foodPerDay, boolean isReadyForSale, int id, String breed, Date bornOn,
               String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.foodPerDay = foodPerDay;
        this.isReadyForSale = isReadyForSale;
    }

    public void feed(float amount) {
        foodPerDay += amount;
    }

    @Override
    public String toString() {
        return "Pig{" +
                "foodPerDay=" + foodPerDay +
                ", isReadyForSale=" + isReadyForSale +
                ", " + super.toString() +
                '}';
    }

    public float getFoodPerDay() {
        return foodPerDay;
    }

    public void setFoodPerDay(float foodPerDay) {
        this.foodPerDay = foodPerDay;
    }

    public boolean isReadyForSale() {
        return isReadyForSale;
    }

    public void setReadyForSale(boolean readyForSale) {
        isReadyForSale = readyForSale;
    }
}
