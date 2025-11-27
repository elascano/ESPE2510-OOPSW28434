package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Customer extends Person {

    public Customer(int id, float weight, int destinationFloor) {
        super(id, weight, destinationFloor);
    }

    @Override
    public String getPersonType() {
        return "Customer";
    }
}
