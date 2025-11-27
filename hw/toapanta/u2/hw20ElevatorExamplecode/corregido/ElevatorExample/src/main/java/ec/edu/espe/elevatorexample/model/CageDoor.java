
package ec.edu.espe.elevatorexample.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class CageDoor extends Door {

    private boolean hasObstructionSensor = true;

    public CageDoor() {
        super();
        System.out.println("  [CageDoor] Obstruction sensor status: " + (hasObstructionSensor ? "Active" : "Inactive") + ".");
    }

    public boolean isHasObstructionSensor() {
        return hasObstructionSensor;
    }

}