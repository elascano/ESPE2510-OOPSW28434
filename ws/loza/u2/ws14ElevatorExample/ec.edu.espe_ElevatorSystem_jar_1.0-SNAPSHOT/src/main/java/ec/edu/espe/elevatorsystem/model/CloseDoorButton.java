/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class CloseDoorButton extends Button {
    public CloseDoorButton() { super("CLOSE_DOOR"); }

    @Override
    public void press() {
        pressed = true;
        System.out.println("[Panel] CLOSE DOOR button pressed");
    }
}

