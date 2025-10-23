/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author ADRIAN TOAPANTA
 */

import simulation.Area;
import enums.Direction;
import java.util.Random;


public class AntEater {
    private int x, y;
    private final Random random;
    
    public AntEater(int x, int y) {
        this.x = x;
        this.y = y;
        this.random = new Random();
    }
    
    public void roam(Area area) {
        Direction[] directions = Direction.values();
        Direction randomDir = directions[random.nextInt(directions.length)];
        
        move(randomDir, area.getWidth(), area.getHeight());
    }
    
    private void move(Direction direction, int width, int height) {
        int newX = x, newY = y;
        
        switch (direction) {
            case NORTH -> newY--;
            case SOUTH -> newY++;
            case EAST -> newX++;
            case WEST -> newX--;
            case NORTHEAST -> { newX++; newY--; }
            case NORTHWEST -> { newX--; newY--; }
            case SOUTHEAST -> { newX++; newY++; }
            case SOUTHWEST -> { newX--; newY++; }
        }
        
        // Verificar límites del área
        if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
            x = newX;
            y = newY;
        }
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
}
