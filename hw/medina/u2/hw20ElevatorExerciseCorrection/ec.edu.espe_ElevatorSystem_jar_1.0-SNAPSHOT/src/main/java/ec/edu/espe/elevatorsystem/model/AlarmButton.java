/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class AlarmButton extends Button implements IAlarm {
    private AlarmSensor alarmSensor;

    public AlarmButton(AlarmSensor sensor) {
        super("ALARM");
        this.alarmSensor = sensor;
    }

    @Override
    public void press() {
        pressed = true;
        System.out.println("[Panel] ALARM button pressed");
        triggerAlarm();
    }

    @Override
    public void triggerAlarm() {
        alarmSensor.triggerAlarm();
    }
}

