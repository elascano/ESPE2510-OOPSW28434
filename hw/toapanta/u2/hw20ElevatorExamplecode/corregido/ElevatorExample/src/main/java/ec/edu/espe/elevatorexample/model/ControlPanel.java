
package ec.edu.espe.elevatorexample.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class ControlPanel {

    private final ControlSystem system;

    public ControlPanel(ControlSystem system) {
        this.system = system;
        System.out.println("  [ControlPanel] User panel initialized.");
    }

    public void selectFloor(int floor) {
        System.out.println("  [ControlPanel] Floor " + floor + " selected internally.");

        system.requestElevator(floor);
    }


    public void triggerAlarm() {
        System.err.println("  [ControlPanel] EMERGENCY ALARM TRIGGERED!");

    }
}