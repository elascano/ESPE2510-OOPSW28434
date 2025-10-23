
package entities;

/**
 *
 * @author ADRIAN TOAPANTA
 */

import enums.Direction;

public class Ant {
    private double food; // en mg
    private double weight;
    private int x, y;
    private boolean hasFood;
    
    public Ant(int x, int y) {
        this.food = 5.0;
        this.weight = 9.4;
        this.x = x;
        this.y = y;
        this.hasFood = false;
    }
    
    public void move(Direction direction) {
        switch (direction) {
            case NORTH -> y--;
            case SOUTH -> y++;
            case EAST -> x++;
            case WEST -> x--;
            case NORTHEAST -> { x++; y--; }
            case NORTHWEST -> { x--; y--; }
            case SOUTHEAST -> { x++; y++; }
            case SOUTHWEST -> { x--; y++; }
        }
        decreaseWeight();
    }
    
    public void eat(Food food) {
        if (food.getAmount() > 0) {
            double eatAmount = Math.min(1.0, food.getAmount());
            food.decrease(eatAmount);
            this.food += eatAmount;
            this.weight += eatAmount * 0.1;
        }
    }
    
    public void decreaseWeight() {
        this.weight -= 0.01;
        this.food -= 0.005;
    }
    
    public void retrieveFood() {
        this.hasFood = true;
        this.food += 1.0;
    }
    
    public void dropFood() {
        this.hasFood = false;
    }
    
    // Getters y setters
    public double getFood() { return food; }
    public double getWeight() { return weight; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean hasFood() { return hasFood; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }
}
