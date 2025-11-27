
package ec.edu.espe.elevatorsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Cage {
    private List<CageDoor> doors;
    private ControlPanel controlPanel;

    public Cage(ControlPanel panel) {
        this.doors = new ArrayList<>();
        this.controlPanel = panel;
        // por simplicidad: una puerta por defecto
        doors.add(new CageDoor());
    }

    public void openDoors() {
        for (CageDoor d : doors) d.open();
    }

    public void closeDoors() {
        for (CageDoor d : doors) d.close();
    }

    public ControlPanel getControlPanel() { return controlPanel; }
}

