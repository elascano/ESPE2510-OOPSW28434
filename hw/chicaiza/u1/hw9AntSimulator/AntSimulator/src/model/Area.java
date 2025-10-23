package model;

import java.util.List;

public class Area {
    private int width;
    private int height;
    private Cell[][] grid;
    private List<Colony> colonies;
    private List<AntEater> antEaters;

    public Area(int width, int height) {
        this.width = width;
        this.height = height;
        // Initialize the grid and lists
    }

    public void runTick() {
        // Here all entities will be updated each tick
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
}