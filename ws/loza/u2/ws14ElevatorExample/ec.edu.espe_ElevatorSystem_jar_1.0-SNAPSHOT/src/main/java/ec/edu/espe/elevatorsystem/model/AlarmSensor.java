/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class AlarmSensor extends Sensor implements IAlarm {

    private boolean alarmTriggered = false;

    public AlarmSensor(int id) {
        super(id);
    }

    @Override
    public boolean read() {
        return alarmTriggered;
    }

    @Override
    public void triggerAlarm() {
        alarmTriggered = true;
        System.out.println("!!! ALARM SENSOR: ALARM TRIGGERED - Elevator stopping immediately !!!");
    }

    public void resetAlarm() {
        alarmTriggered = false;
    }
}

