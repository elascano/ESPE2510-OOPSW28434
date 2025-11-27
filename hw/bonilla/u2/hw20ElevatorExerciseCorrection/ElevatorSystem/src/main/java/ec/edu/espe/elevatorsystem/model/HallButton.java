package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class HallButton extends Button {

    private Direction direction;
    private int floorNumber;
    private ElevatorSystem elevatorSystem;

    public HallButton(int floorNumber, Direction direction) {
        this.floorNumber = floorNumber;
        this.direction = direction;
    }

    @Override
    public void press() {
        System.out.println("HallButton: Button pressed for " + direction + " on floor " + floorNumber);
    }

    @Override
    public boolean isActive() {
        System.out.println("HallButton: Checking if button is active");
        return true;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
