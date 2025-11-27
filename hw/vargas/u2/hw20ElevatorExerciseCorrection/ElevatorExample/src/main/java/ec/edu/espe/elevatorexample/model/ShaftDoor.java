
package ec.edu.espe.elevatorexample.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class ShaftDoor {

    private final int floorLocation;

    public ShaftDoor(int floorLocation) {
        this.floorLocation = floorLocation;
        System.out.println("  [ShaftDoor] Door at floor " + floorLocation + " initialized.");
    }

    public void visualizeOpen() {
        System.out.println("  [ShaftDoor - Floor " + floorLocation + "] Visual: DOOR IS OPEN.");
    }

    public void visualizeClosed() {
        System.out.println("  [ShaftDoor - Floor " + floorLocation + "] Visual: DOOR IS CLOSED.");
    }

    public int getFloorLocation() {
        return floorLocation;
    }
}