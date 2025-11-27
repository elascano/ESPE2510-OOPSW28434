
package ec.edu.espe.elevatorexample.model;

/**
 *
 * @author Cesar Vargas, Paradigm, @ESPE
 */
public class HallButton {
    private final int floorLocation;
    private final ControlSystem controlSystem;

    public HallButton(int floorLocation, ControlSystem controlSystem) {
        this.floorLocation = floorLocation;
        this.controlSystem = controlSystem;
    }

    public void press(int destinationFloor) {
        System.out.println("User at floor " + floorLocation + " requested to go to floor " + destinationFloor + ".");
        

        controlSystem.requestElevator(destinationFloor);
    }
}