
package ec.edu.espe.elevatorsystem.model;

public class Person {
    private int id;
    private float weight;
    private int type; 

    public Person(int id, float weight, int type) {
        this.id = id;
        this.weight = weight;
        this.type = type;
    }

    public void pressFloorButton(FloorButton button) {
        button.press();
    }

    public void pressCageButton(CageButton button) {
        button.press();
    }

    // getters
    public int getId() { return id; }
    public float getWeight() { return weight; }
}
