package ec.edu.espe.farmsymulator.model;

import java.util.Date;

public class Cow extends FarmAnimal {
    private boolean isProducingMilk;
    private float litersADay;

    public Cow(boolean isProducingMilk, float litersADay, int id, String breed, 
               Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.litersADay = litersADay;
    }

    @Override
    public String toString() {
        return "Cow{" + "isProducingMilk=" + isProducingMilk + 
                ", litersADay=" + litersADay + ", " + super.toString() + '}';
    }

    public float milk() {
        if (isProducingMilk) {
            return litersADay;
        }
        return 0;
    }

    public boolean isIsProducingMilk() {
        return isProducingMilk;
    }

    public void setIsProducingMilk(boolean isProducingMilk) {
        this.isProducingMilk = isProducingMilk;
    }

    public float getLitersADay() {
        return litersADay;
    }

    public void setLitersADay(float litersADay) {
        this.litersADay = litersADay;
    }
}