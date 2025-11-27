/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class FloorButton extends Button {
    private int floor;

    public FloorButton(int floor) {
        super("FLOOR_" + floor);
        this.floor = floor;
    }

    public int getFloor() { return floor; }

    @Override
    public void press() {
        pressed = true;
        System.out.println("[Cabin] Floor button " + floor + " pressed");
    }
}

