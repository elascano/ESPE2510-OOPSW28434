/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

/**
 *
 * @author Mateo Cevallos
 */
public class AlarmButton extends Button{
    public AlarmButton(int buttonType, int floorNumber) {
        super(buttonType, floorNumber);
    }

    public void activateAlarm() {
        System.out.println("Alarm activated!");
    }
}
