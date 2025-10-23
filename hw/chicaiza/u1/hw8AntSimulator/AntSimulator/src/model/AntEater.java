/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Daniel
 */
public class AntEater {
    private Cell position;
    private int eatenSinceSleep;
    private int eatingTicksLeft;
    private int sleepingTicksLeft;

    public AntEater() { this.eatenSinceSleep = 0; }

    public AntEater(Cell position) {
        this();
        this.position = position;
    }

    public Cell getPosition() { return position; }
    public void setPosition(Cell position) { this.position = position; }

    public void tick() {
        if (eatingTicksLeft > 0) eatingTicksLeft--;
        if (sleepingTicksLeft > 0) sleepingTicksLeft--;
    }

    public boolean isEating() { return eatingTicksLeft > 0; }
    public boolean isSleeping() { return sleepingTicksLeft > 0; }

    public void startEating() {
        eatingTicksLeft = 10;
        eatenSinceSleep++;
    }

    public void finishEating() {
        if (eatenSinceSleep >= 50) {
            // go to sleep for 600 ticks
            sleepingTicksLeft = 600;
            eatenSinceSleep = 0;
        }
    }

    // simple action placeholders
    public void wander() {}
}
