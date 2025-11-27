package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class ShaftDoor {

    private String doorId;
    private int floorNumber;
    private boolean isOpen;
    private boolean isLocked;

    public ShaftDoor(String doorId, int floorNumber) {
        this.doorId = doorId;
        this.floorNumber = floorNumber;
        this.isOpen = false;
        this.isLocked = false;
    }

    public void open() {
        System.out.println("Shaft door " + doorId + " opening on floor " + floorNumber);
        isOpen = true;
    }

    public void close() {
        System.out.println("Shaft door " + doorId + " closing on floor " + floorNumber);
        isOpen = false;
    }

    public boolean checkForObstruction() {
        System.out.println("Checking for obstruction in shaft door " + doorId);
        return false;
    }
}
