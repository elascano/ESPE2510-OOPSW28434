package ec.edu.espe.farmsymulator.model;

import java.util.Date;

/**
 *
 * @author Bryan Gudino
 */
public class Pig extends FarmAnimal {

    public Pig(int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    }

    @Override
    public String toString() {
        
        return "Pig{" + super.toString() + '}';
    }
    
}