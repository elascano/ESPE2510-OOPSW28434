
package ec.edu.espe.elevatorsystem.model;

import java.util.ArrayList;
import java.util.List;

public class ControlPanel {
    private List<CageButton> cageButtons;
    private List<FloorButton> floorButtons;

    public ControlPanel() {
        cageButtons = new ArrayList<>();
        floorButtons = new ArrayList<>();
    }

    public void pressButton(int buttonId) {
        for (CageButton b : cageButtons) {
            if (b.getId() == buttonId) { b.press(); return; }
        }
        for (FloorButton f : floorButtons) {
            if (f.getId() == buttonId) { f.press(); return; }
        }
        System.out.println("Button " + buttonId + " not found");
    }

    public void addCageButton(CageButton b) { cageButtons.add(b); }
    public void addFloorButton(FloorButton b) { floorButtons.add(b); }
}
