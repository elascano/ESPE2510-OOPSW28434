/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class Motor {
    private int power;
    private boolean running;

    public Motor(int power) {
        this.power = power;
        this.running = false;
    }

    public void start() { running = true; System.out.println("[Motor] Started"); }
    public void stop()  { running = false; System.out.println("[Motor] Stopped"); }
    public boolean isRunning() { return running; }
}

