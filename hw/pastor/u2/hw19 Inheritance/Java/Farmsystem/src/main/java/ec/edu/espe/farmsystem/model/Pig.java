package ec.edu.espe.farmsystem.model;
import java.util.Date;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Pig extends FarmAnimal{
    private boolean isReadyForSlaughter;

    public Pig(boolean isReadyForSlaughter, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location);
        this.isReadyForSlaughter = isReadyForSlaughter;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               " > Ready to Kill : " + (isReadyForSlaughter ? "YES" : "No") + "\n" +
               "========================================";
    }

    public boolean isIsReadyForSlaughter() {
        return isReadyForSlaughter;
    }

    public void setIsReadyForSlaughter(boolean isReadyForSlaughter) {
        this.isReadyForSlaughter = isReadyForSlaughter;
    }
}
