package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Thais Santórum
 */
public class FarmAnimal {
    int id;
    String breed;
    Date bornOn;
    String gender;
    boolean isAbleToReproduce;
    float weight;
    Cage cage;

    public FarmAnimal(int id, String breed, Date bornOn, String gender, boolean ableToReproduce, float weight, Cage cage) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
    public int getAgeInMonths(){
        //TODO comute the age in months
        return 0;
    }
    
    
    public void assignCage(Cage cage){
        this.cage = cage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Date getBornOn() {
        return bornOn;
    }

    public void setBornOn(Date bornOn) {
        this.bornOn = bornOn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isIsAbleToReproduce() {
        return isAbleToReproduce;
    }

    public void setIsAbleToReproduce(boolean isAbleToReproduce) {
        this.isAbleToReproduce = isAbleToReproduce;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Cage getCage() {
        return cage;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
    }
    
    
    
    
    
}
