<<<<<<< HEAD
package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public abstract class FarmAnimal {
    private int id;
    private String breed;
    private Date bornOn;
    private String gendeR;
    private boolean isAbleToReproduce;
    private float weight;
    private Cage cage;
    private Location location;

    @Override
    public String toString() {
        return "FarmAnimal{" + "id=" + id + ", breed=" + breed + ", bornOn=" + bornOn + ", gendeR=" + gendeR + ", isAbleToReproduce=" + isAbleToReproduce + ", weight=" + weight + ", cage=" + cage + ", location=" + location + '}';
    }
      
    public FarmAnimal(int id, String breed, Date bornOn, String gendeR, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
        this.gendeR = gendeR;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
        this.location = location;
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

    public String getGendeR() {
        return gendeR;
    }

    public void setGendeR(String gendeR) {
        this.gendeR = gendeR;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
   
}

    
    
    
=======
package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public abstract class FarmAnimal {
    private int id;
    private String breed;
    private Date bornOn;
    private String gendeR;
    private boolean isAbleToReproduce;
    private float weight;
    private Cage cage;
    private Location location;

    @Override
    public String toString() {
        return "FarmAnimal{" + "id=" + id + ", breed=" + breed + ", bornOn=" + bornOn + ", gendeR=" + gendeR + ", isAbleToReproduce=" + isAbleToReproduce + ", weight=" + weight + ", cage=" + cage + ", location=" + location + '}';
    }
      
    public FarmAnimal(int id, String breed, Date bornOn, String gendeR, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
        this.gendeR = gendeR;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
        this.location = location;
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

    public String getGendeR() {
        return gendeR;
    }

    public void setGendeR(String gendeR) {
        this.gendeR = gendeR;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
   
}

    
    
    
>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
    