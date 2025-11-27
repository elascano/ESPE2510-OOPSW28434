/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public abstract class Button {
    protected String label;
    protected boolean pressed;

    public Button(String label) {
        this.label = label;
        this.pressed = false;
    }

    public String getLabel() { return label; }
    public boolean isPressed() { return pressed; }
    public void reset() { pressed = false; }
    public abstract void press();
}

