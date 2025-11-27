
package ec.edu.espe.elevatorsystem.model;

public class FloorSelectButton extends CageButton {
    private int floor;

    public FloorSelectButton(int id, int floor) {
        super(id);
        this.floor = floor;
    }

    @Override
    public void press() {
        isPressed = true;
        System.out.println("FloorSelectButton " + id + " pressed for floor " + floor);
    }

    public int getFloor() { return floor; }
}
