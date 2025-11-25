package ec.edu.espe.farmsystem.model;
import java.util.Date;

/**
 *
 * @author Mathews Pastor, POOwer Ranger of Programing, @ESPE
 */
public abstract class FarmAnimal {
    int id;
    String breed;
    Date bornOn;
    String gender;
    boolean isAbleToReproduce;
    float weight;
    Cage cage;
    Location location;
    
    public int getAgeInMounths(){
        //TODO compute the age in mounths
        return 0;
    }
    
    public void assignCage(Cage cage){
        this.cage = cage;
    }

    public FarmAnimal(int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
        this.gender = gender;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
        this.location = location;
    }

    @Override
    public String toString() {
        return "FarmAnimal{" + "id=" + id + ", breed=" + breed + ", bornOn=" + bornOn + ", gender=" + gender + ", isAbleToReproduce=" + isAbleToReproduce + ", weight=" + weight + ", cage=" + cage + ", location=" + location + '}';
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
