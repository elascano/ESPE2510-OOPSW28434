/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class UpButton extends Button {
    public UpButton() { super("UP"); }

    @Override
    public void press() {
        pressed = true;
        System.out.println("[Panel] UP button pressed");
    }
}

