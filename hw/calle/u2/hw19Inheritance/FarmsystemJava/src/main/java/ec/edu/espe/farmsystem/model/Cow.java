package ec.edu.espe.farmsystem.model;

import java.util.Date;

public class Cow extends FarmAnimal {
    private boolean isProducingMilk;
    private float littersADay;

    public Cow(boolean isProducingMilk, float littersADay, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.littersADay = littersADay;
    }

    public float milk() {
        if (isProducingMilk) {
            System.out.println("Cow milked: " + littersADay + " liters.");
            return littersADay;
        } else {
            System.out.println("The cow is not producing milk at this time.");
            return 0.0f;
        }
    }

    @Override
    public String toString() {
        return "Cow{" + "isProducingMilk=" + isProducingMilk + ", littersADay=" + littersADay + ", " + super.toString() + '}';
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