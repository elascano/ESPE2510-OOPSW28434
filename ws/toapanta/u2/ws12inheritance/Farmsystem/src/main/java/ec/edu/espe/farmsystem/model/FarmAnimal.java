
package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Adrian Toapanta, Student OOP, @ESPE
 */
public class FarmAnimal {

    public FarmAnimal(int par, String breed1, Date bornOn1, boolean abelToReproduce, float weigth1, Location location, String gender1, int id1, Cage cage1) {
    }
     private int id;
     private String breed;
     private Date bornOn;
     private String gender ;
     private boolean isAbelToReproduce;
     private float weigth;
     private Cage cage;

    @Override
    public String toString() {
        return "FarmAnimal{" + "id=" + id + ", breed=" + breed + ", bornOn=" + bornOn + ", gender=" + gender + ", isAbelToReproduce=" + isAbelToReproduce + ", weigth=" + weigth + ", cage=" + cage + '}';
    }
     
     
     public int getAgeInMonts(){
         //Todo complute i
         return 0;    
     }
     public void assignCage(Cage cage){
         this.setCage(cage);
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
     * @return the isAbelToReproduce
     */
    public boolean isIsAbelToReproduce() {
        return isAbelToReproduce;
    }

    /**
     * @param isAbelToReproduce the isAbelToReproduce to set
     */
    public void setIsAbelToReproduce(boolean isAbelToReproduce) {
        this.isAbelToReproduce = isAbelToReproduce;
    }

    /**
     * @return the weigth
     */
    public float getWeigth() {
        return weigth;
    }

    /**
     * @param weigth the weigth to set
     */
    public void setWeigth(float weigth) {
        this.weigth = weigth;
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
