
package ec.edu.espe.elevatorsystem.model;

public class Floor {
    
    public static final Floor[] floors = new Floor[20];
    public Elevator[] elevators = new Elevator[3];

    private int floorNumber;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int goUp() {
        return Math.min(19, floorNumber + 1);
    }

    public int goDown() {
        return Math.max(0, floorNumber - 1);
    }

    public int getFloorNumber() { return floorNumber; }
}
