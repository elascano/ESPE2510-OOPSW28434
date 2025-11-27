package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Employee extends Person {

    public Employee(int id, float weight, int destinationFloor) {
        super(id, weight, destinationFloor);
    }

    @Override
    public String getPersonType() {
        return "Employee";
    }
}
