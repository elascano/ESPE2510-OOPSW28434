package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Visitor extends Person {

    public Visitor(int id, float weight, int destinationFloor) {
        super(id, weight, destinationFloor);
    }

    @Override
    public String getPersonType() {
        return "Visitor";
    }
}
