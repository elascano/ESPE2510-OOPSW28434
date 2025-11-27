package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class FloorButton extends Button {

    private int targetFloor;

    public FloorButton(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    @Override
    public void press() {
        System.out.println("FloorButton: Floor button pressed for floor " + targetFloor);
    }

    @Override
    public boolean isActive() {
        System.out.println("FloorButton: Checking if button is active");
        return true;
    }

    public int getTargetFloor() {
        return targetFloor;
    }
}
