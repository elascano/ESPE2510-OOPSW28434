/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

/**
 *
 * @author Mateo Cevallos
 */
public abstract class Button {
    private int buttonType;
    private int floorNumber;
    private boolean isPressed;

    public Button(int buttonType, int floorNumber) {
        this.buttonType = buttonType;
        this.floorNumber = floorNumber;
        this.isPressed = false;
    }

    public void press() {
        isPressed = true;
        System.out.println("Button pressed at floor " + floorNumber);
    }

    public void release() {
        isPressed = false;
        System.out.println("Button released");
    }
}
