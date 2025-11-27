package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Pig extends FarmAnimal {

    public Pig(int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    }
    
    @Override
    public String toString() {
        return "Pig{" + super.toString() + super.toString()+'}';
    }
}