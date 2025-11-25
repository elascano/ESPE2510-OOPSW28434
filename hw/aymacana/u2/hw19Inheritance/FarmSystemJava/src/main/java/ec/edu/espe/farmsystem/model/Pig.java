package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Pig extends FarmAnimal {

    @Override
    public String toString() {
        return "Pig{" + ", " + super.toString() + '}';
    }

    public Pig(int id, String breet, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breet, bornOn, gender, isAbleToReproduce, weight, cage);
    }
}
