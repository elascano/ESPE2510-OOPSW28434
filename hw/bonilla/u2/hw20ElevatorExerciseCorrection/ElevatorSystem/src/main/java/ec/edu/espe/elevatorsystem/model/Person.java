package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public abstract class Person {

    private int id;
    private float weight;
    private int destinationFloor;

    public Person(int id, float weight, int destinationFloor) {
        this.id = id;
        this.weight = weight;
        this.destinationFloor = destinationFloor;
    }

    public void pressHallButton(Floor floor, Direction dir) {
        System.out.println("Person " + id + " pressed hall button on floor " + floor.getFloorNumber() + " direction " + dir);
    }

    public void pressFloorButton(int floor) {
        System.out.println("Person " + id + " pressed floor button for floor " + floor);
    }

    public int getId() {
        return id;
    }

    public float getWeight() {
        return weight;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public abstract String getPersonType();
}
