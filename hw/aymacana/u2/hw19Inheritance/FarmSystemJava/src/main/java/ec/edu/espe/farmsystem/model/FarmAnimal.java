package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public abstract class FarmAnimal {

    private int id;
    private String breet;
    private Date bornOn;
    private String gender;
    private boolean isAbleToReproduce;
    private float weight;
    private Cage cage;

    public int getAgeInMonths() {
        //TODO compute the age in months
        return 0;
    }

    public void assignCage(Cage cage) {
        this.setCage(cage);
    }

    public FarmAnimal(int id, String breet, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        this.id = id;
        this.breet = breet;
        this.bornOn = bornOn;
        this.gender = gender;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
    }

    @Override
    public String toString() {
        return "FarmAnimal{" + "id=" + id + ", breet=" + breet + ", bornOn=" + bornOn + ", gender=" + gender + ", isAbleToReproduce=" + isAbleToReproduce + ", weight=" + weight + ", cage=" + cage + '}';
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
     * @return the breet
     */
    public String getBreet() {
        return breet;
    }

    /**
     * @param breet the breet to set
     */
    public void setBreet(String breet) {
        this.breet = breet;
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
