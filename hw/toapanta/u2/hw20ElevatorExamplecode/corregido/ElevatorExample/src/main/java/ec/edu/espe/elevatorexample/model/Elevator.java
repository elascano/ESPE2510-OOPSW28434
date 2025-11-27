
package ec.edu.espe.elevatorexample.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class Elevator {
    private final int id;
    private int currentFloor;
    private final MotorSystem motor;


    private boolean doorOpen;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1; 
        this.motor = new MotorSystem();
        this.doorOpen = true; 
        System.out.println("Elevator " + this.id + " initialized at floor " + this.currentFloor + ".");
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public MotorSystem getMotor() {
        return motor;
    }


    public void openDoor() {
        if (!doorOpen) {
            doorOpen = true;
            System.out.println("Doors OPEN.");
        }
    }


    public void closeDoor() {
        if (doorOpen) {
            doorOpen = false;
            System.out.println(" Doors CLOSE.");
        }
    }
}