
package ec.edu.espe.elevatorexample.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class PanelControl {

    private final List<PanelButton> buttons;

    public PanelControl() {
        this.buttons = new ArrayList<>();

        buttons.add(new PanelButton("1"));
        buttons.add(new PanelButton("2"));
        System.out.println("  [PanelControl] Display and interface initialized.");
    }

    public void displayCurrentFloor(int floor) {
        System.out.println("  [PanelControl] DISPLAY: Current Floor is " + floor + ".");
    }


}
