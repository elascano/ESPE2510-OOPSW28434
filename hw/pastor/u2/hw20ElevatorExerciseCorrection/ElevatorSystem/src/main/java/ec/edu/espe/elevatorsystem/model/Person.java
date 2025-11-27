package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Person {
    private int id;
    private float weight;
    private int destinationFloor;

    public float pressButton(Button button) {
        System.out.println("Person pressed button: " + button.getName());
        return 1.0f; 
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
}
