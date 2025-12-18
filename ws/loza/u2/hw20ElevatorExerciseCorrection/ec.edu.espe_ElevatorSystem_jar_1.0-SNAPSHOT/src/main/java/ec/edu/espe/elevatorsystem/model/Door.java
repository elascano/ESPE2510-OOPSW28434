/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class Door {
    private boolean open;

    public Door() { open = false; }

    public void open() {
        open = true;
        System.out.println("[Door] Door opened");
    }

    public void close() {
        open = false;
        System.out.println("[Door] Door closed");
    }

    public boolean isOpen() { return open; }
}

