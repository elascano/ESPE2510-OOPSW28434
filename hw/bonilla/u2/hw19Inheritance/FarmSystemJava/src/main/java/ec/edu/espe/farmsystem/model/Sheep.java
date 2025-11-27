package ec.edu.espe.farmsystem.model;
import java.util.Date;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class Sheep extends FarmAnimal {
    private Date lastShearing;

    public Sheep(Date lastShearing, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastShearing = lastShearing;
    }

    @Override
    public String toString() {
        return "{\n" +
            "  \"lastShearing\": \"" + lastShearing + "\",\n" +
            "  \"farmAnimal\": " + super.toString() + "\n" +
            "}";
    }

    
    /**
     * @return the lastShearing
     */
    public Date getLastShearing() {
        return lastShearing;
    }

    /**
     * @param lastShearing the lastShearing to set
     */
    public void setLastShearing(Date lastShearing) {
        this.lastShearing = lastShearing;
    }
    
    public void cutWhool(float kilogramsOfWool) {
        kilogramsOfWool++;
        
    }
    
    public void shear(String shearedSheep) {
        shearedSheep = "The sheep has been sheared";
        
    }
}
