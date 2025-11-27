package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
import java.util.ArrayList;
import java.util.List;

public class Floor {

    private int floorNumber;
    private HallButton upButton;
    private HallButton downButton;
    private List<ShaftDoor> shaftDoors;

    public Floor(int floorNumber, Direction upDirection, Direction downDirection) {
        this.floorNumber = floorNumber;
        this.upButton = new HallButton(floorNumber, upDirection);
        this.downButton = new HallButton(floorNumber, downDirection);
        this.shaftDoors = new ArrayList<>();
    }

    public void pressUpButton() {
        System.out.println("UP button pressed on floor " + floorNumber);
        upButton.press();
    }

    public void pressDownButton() {
        System.out.println("DOWN button pressed on floor " + floorNumber);
        downButton.press();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

}
