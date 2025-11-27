/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
import java.util.HashMap;
import java.util.Map;

public class CabinPanel extends Panel {
    private Map<Integer, FloorButton> floorButtons = new HashMap<>();
    private AlarmButton alarmButton;
    private OpenDoorButton openDoorButton = new OpenDoorButton();
    private CloseDoorButton closeDoorButton = new CloseDoorButton();

    public CabinPanel(int id, AlarmSensor alarmSensor, int maxFloors) {
        super(id);
        this.alarmButton = new AlarmButton(alarmSensor);
        addButton(alarmButton);
        addButton(openDoorButton);
        addButton(closeDoorButton);

        for (int f = 1; f <= maxFloors; f++) {
            FloorButton fb = new FloorButton(f);
            floorButtons.put(f, fb);
            addButton(fb);
        }
    }

    public FloorButton getFloorButton(int floor) {
        return floorButtons.get(floor);
    }

    public AlarmButton getAlarmButton() { return alarmButton; }
    public OpenDoorButton getOpenDoorButton() { return openDoorButton; }
    public CloseDoorButton getCloseDoorButton() { return closeDoorButton; }
}

