/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;


import java.util.Random;

public class Simulator {
    private Area area;
    private int tickDurationMs;
    private long currentTick;
    private Random random;

    public Simulator() {
        this.random = new Random();
    }

    public Simulator(Area area, int tickDurationMs) {
        this();
        this.area = area;
        this.tickDurationMs = tickDurationMs;
    }

    public Area getArea() { return area; }
    public void setArea(Area area) { this.area = area; }

    public int getTickDurationMs() { return tickDurationMs; }
    public void setTickDurationMs(int tickDurationMs) { this.tickDurationMs = tickDurationMs; }

    public long getCurrentTick() { return currentTick; }

    // Advance the world by one tick (skeleton)
    public void tick() {
        currentTick++;
        // 1) update ants
        if (area != null) {
            for (Colony col : area.getColonies()) {
                for (Ant ant : col.getAnts()) {
                    ant.tick();
                    // Basic behavior placeholder:
                    // if ant in nest and weight >=3 it could leave (movement not implemented)
                    // if ant carrying, drop pheromone in its cell
                    Cell pos = ant.getPosition();
                    if (pos != null && ant.getCarrying() > 0) {
                        pos.addPheromone(ant.pheromoneToDrop());
                    }
                }
            }

            // 2) decrease pheromone levels in all cells
            for (int r=0; r<area.getHeight(); r++) {
                for (int c=0; c<area.getWidth(); c++) {
                    Cell cell = area.getCell(r, c);
                    PheromoneDrop p = cell.getPheromone();
                    if (p != null) {
                        p.decrease();
                        if (!p.isAlive()) cell.setPheromone(null);
                    }
                }
            }

            // 3) update ant eaters
            for (AntEater ae : area.getAntEaters()) {
                ae.tick();
            }
        }
    }
}
