package ec.edu.espe.farmsystem.model;
import java.util.Date;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Sheep extends FarmAnimal{
   private Date lastSheering;

    public Sheep(Date lastSheering, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        this.lastSheering = lastSheering;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               " > Last Sheering : " + lastSheering + "\n" +
               "========================================";
    }

    public void shear() {
        this.lastSheering = new Date();
    }

    public Date getLastSheering() {
        return lastSheering;
    }

    public void setLastSheering(Date lastSheering) {
        this.lastSheering = lastSheering;
    } 
}
