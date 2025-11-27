package ec.edu.espe.farmsystem.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public abstract class FarmAnimal {
    private int id;
    private String breed;
    private Date bornOn;
    private String gender;
    private boolean isAbleToReproduce;
    private float weight;
    private Cage cage;
    
    public FarmAnimal(int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
        this.gender = gender;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
    }
    
    public int getAgeInMonths(){
        // Convierte java.util.Date a LocalDate para calcular la edad con java.time
        Instant instant = bornOn.toInstant();
        LocalDate bornDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        
        Period period = Period.between(bornDate, currentDate);
        
        int totalMonths = period.getYears() * 12 + period.getMonths();
        
        return totalMonths;
    }
    
    public void assignCage(Cage cage){
        this.setCage(cage);
    }

    @Override
    public String toString() {
        return "FarmAnimal{id=" + id + ", breed=" + breed + ", bornOn=" + bornOn + ", gender=" + gender + ", isAbleToReproduce=" + isAbleToReproduce + ", weight=" + weight + ", cage=" + cage + ", ageInMonths=" + getAgeInMonths() + '}';
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