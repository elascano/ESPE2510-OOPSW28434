<<<<<<< HEAD
package ec.edu.espe.farmsystem.model;
import java.util.Date;

/**
 *
 * @author Joseph B. Medina
 */
public class Cow extends FarmAnimal{
    
    private boolean isProducingMilk;
    private float litersADay;

    public Cow(boolean isProducingMilk, float litersADay, int id, String breed, Date bornOn, String gendeR, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
        this.isProducingMilk = isProducingMilk;
        this.litersADay = litersADay;
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

    @Override
    public String toString() { 
        return "Cow{" + "isProducingMilk=" + isProducingMilk + ", litersADay=" + litersADay + ", " + super.toString() + '}';
    }
    
}
=======
package ec.edu.espe.farmsystem.model;
import java.util.Date;

/**
 *
 * @author Joseph B. Medina
 */
public class Cow extends FarmAnimal{
    
    private boolean isProducingMilk;
    private float litersADay;

    public Cow(boolean isProducingMilk, float litersADay, int id, String breed, Date bornOn, String gendeR, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
        this.isProducingMilk = isProducingMilk;
        this.litersADay = litersADay;
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

    @Override
    public String toString() { 
        return "Cow{" + "isProducingMilk=" + isProducingMilk + ", litersADay=" + litersADay + ", " + super.toString() + '}';
    }
    
}
>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
