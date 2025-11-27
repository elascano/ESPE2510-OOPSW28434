package ec.edu.espe.ElevatorSystem.model;

/**
 * @author JOSUE ROJAS y THAIS SANTORUM
 */

import java.util.ArrayList;
import java.util.List;

public class ControlPanel {
    private List<Button> buttons;

    public ControlPanel() {
        this.buttons = new ArrayList<>();
    }

    // Methods strictly from UML
    public void openTheDoor() {
        System.out.println("[ControlPanel] Opening doors...");
    }

    public void closeDoor() {
        System.out.println("[ControlPanel] Closing doors...");
    }

    public void selectFloor() {
        // Logic handled by Controller, but method exists here per UML
        System.out.println("[ControlPanel] Floor selection active.");
    }

    public void triggerTheAlarm() {
        System.out.println("[ControlPanel] ALARM TRIGGERED! Calling security...");
    }

    public void stopElevator() {
        System.out.println("[ControlPanel] EMERGENCY STOP ACTIVATED.");
    }
}