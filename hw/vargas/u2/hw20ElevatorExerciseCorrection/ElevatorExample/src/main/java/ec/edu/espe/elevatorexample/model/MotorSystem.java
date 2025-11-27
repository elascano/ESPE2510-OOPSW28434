
package ec.edu.espe.elevatorexample.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class MotorSystem {


    public void moveUp(int currentFloor) {
        System.out.println("   Moving UP from floor " + currentFloor + "...");

    }


    public void moveDown(int currentFloor) {
        System.out.println("   Moving DOWN from floor " + currentFloor + "...");
    }


    public void applyBrakes(int floor) {
        System.out.println("   Applying brakes. Stopped at floor " + floor + ".");
    }
}