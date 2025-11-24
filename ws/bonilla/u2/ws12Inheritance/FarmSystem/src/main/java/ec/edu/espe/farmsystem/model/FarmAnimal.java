package ec.edu.espe.farmsystem.model;
import java.util.Date;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public abstract class FarmAnimal {
    private int id;
    private String breed;
    private Date bornOn;
    private String gender;
    private boolean isAbleToReproduce;
    private float weight;
    private Cage cage;
    
    public int getAgeInMonths(){
        //T0D0 compute the age in months
        return 0;
    }

    @Override
    public String toString() {
        return "FarmAnimal{" + "id = " + getId() + ", breed = " + getBreed() + ", bornOn = " + getBornOn() + ", gender = " + getGender() + ", isAbleToReproduce = " + isIsAbleToReproduce() + ", weight = " + getWeight() + ", cage = " + getCage() + '}';
    }
    
    public void assignCage(Cage cage){
        this.setCage(cage);
    }

    public FarmAnimal(int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
        this.gender = gender;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * @param breed the breed to set
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * @return the bornOn
     */
    public Date getBornOn() {
        return bornOn;
    }

    /**
     * @param bornOn the bornOn to set
     */
    public void setBornOn(Date bornOn) {
        this.bornOn = bornOn;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the isAbleToReproduce
     */
    public boolean isIsAbleToReproduce() {
        return isAbleToReproduce;
    }

    /**
     * @param isAbleToReproduce the isAbleToReproduce to set
     */
    public void setIsAbleToReproduce(boolean isAbleToReproduce) {
        this.isAbleToReproduce = isAbleToReproduce;
    }

    /**
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * @return the cage
     */
    public Cage getCage() {
        return cage;
    }

    /**
     * @param cage the cage to set
     */
    public void setCage(Cage cage) {
        this.cage = cage;
    }
    
    
}
