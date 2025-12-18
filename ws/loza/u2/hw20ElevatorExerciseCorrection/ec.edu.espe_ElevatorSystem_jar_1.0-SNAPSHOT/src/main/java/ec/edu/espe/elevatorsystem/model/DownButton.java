/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class DownButton extends Button {
    public DownButton() { super("DOWN"); }

    @Override
    public void press() {
        pressed = true;
        System.out.println("[Panel] DOWN button pressed");
    }
}
