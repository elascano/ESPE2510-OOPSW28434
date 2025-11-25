package ec.edu.espe.farmsymulator.model;

import java.util.Date;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */
public class Pig extends FarmAnimal {

    @Override
    public String toString() {
        return "Pig{" + super.toString()+'}';
    }

    
    
    public Pig(int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    }

    
}
