/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author ADRIAN TOAPANTA
 */

public class Nest {
    private int x, y;
    private Colony colony;
    
    public Nest(int x, int y, Colony colony) {
        this.x = x;
        this.y = y;
        this.colony = colony;
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public Colony getColony() { return colony; }
}
