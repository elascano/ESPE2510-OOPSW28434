/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Daniel
 */
public class Ant {
    private Cell position;
    private int weight; // mg
    private int carrying; // mg (max 5)
    private int ticksSinceLastMetabolism;
    private Direction heading;

    public enum Direction { N, S, E, W, NE, NW, SE, SW, NONE }

    public Ant() {
        this.weight = 1;
        this.carrying = 0;
        this.ticksSinceLastMetabolism = 0;
        this.heading = Direction.NONE;
    }

    public Ant(Cell position) {
        this();
        this.position = position;
    }

    // getters & setters
    public Cell getPosition() { return position; }
    public void setPosition(Cell position) { this.position = position; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = Math.max(0, Math.min(5, weight)); }

    public int getCarrying() { return carrying; }
    public void setCarrying(int carrying) { this.carrying = Math.max(0, Math.min(5, carrying)); }

    public Direction getHeading() { return heading; }
    public void setHeading(Direction heading) { this.heading = heading; }

    // behavior skeletons
    public boolean canLeaveNest() {
        return weight >= 3;
    }

    // called each tick
    public void tick() {
        ticksSinceLastMetabolism++;
        if (ticksSinceLastMetabolism >= 50) {
            // metabolism: lose 1 mg every 50 ticks
            setWeight(weight - 1);
            ticksSinceLastMetabolism = 0;
        }
    }

    // pick up food (up to capacity)
    public int pickUpFood(int available) {
        int capacity = 5 - carrying;
        int take = Math.min(capacity, available);
        carrying += take;
        return take;
    }

    // drop carried food into nest
    public int dropFoodIntoNest() {
        int dropped = carrying;
        carrying = 0;
        return dropped;
    }

    // create pheromone when carrying
    public int pheromoneToDrop() {
        return carrying > 0 ? 100 : 0;
    }
}
