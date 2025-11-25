package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class Pig extends FarmAnimal {

    @Override
    public String toString() {
        return "Pig{" + 
                "\n " +super.toString()+             
                "\n}";
    }

    
    public Pig(int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    }
    
     
}
