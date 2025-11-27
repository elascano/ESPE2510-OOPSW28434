package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class Pig extends FarmAnimal {

    public Pig(int id, String breed, Date bornOn, String gendeR, boolean isAbleToReproduce, float weight, Cage cage, Location location) {
        super(id, breed, bornOn, gendeR, isAbleToReproduce, weight, cage, location);
    }

    @Override
    public String toString() {
        return "Pig{, " + super.toString() + '}';
    }
}
