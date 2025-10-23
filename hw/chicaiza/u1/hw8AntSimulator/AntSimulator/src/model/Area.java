/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Area {
    private int width;
    private int height;
    private Cell[][] grid;
    private List<Colony> colonies;
    private List<AntEater> antEaters;

    public Area() {}

    public Area(int width, int height) {
        this.width = width;
        this.height = height;
        this.colonies = new ArrayList<>();
        this.antEaters = new ArrayList<>();
        initGrid();
    }

    private void initGrid() {
        grid = new Cell[height][width];
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                grid[r][c] = new Cell(r, c);
            }
        }
    }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public Cell getCell(int row, int col) {
        if (row < 0 || col < 0 || row >= height || col >= width) return null;
        return grid[row][col];
    }

    public void addColony(Colony c) { colonies.add(c); }

    public List<Colony> getColonies() { return colonies; }

    public void addAntEater(AntEater ae) { antEaters.add(ae); }

    public List<AntEater> getAntEaters() { return antEaters; }

    // helper to get neighbors (4-way). Expand later to 8-way.
    public List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        if (cell == null) return neighbors;
        int r = cell.getRow();
        int c = cell.getCol();
        Cell n;
        n = getCell(r-1, c); if (n!=null) neighbors.add(n);
        n = getCell(r+1, c); if (n!=null) neighbors.add(n);
        n = getCell(r, c-1); if (n!=null) neighbors.add(n);
        n = getCell(r, c+1); if (n!=null) neighbors.add(n);
        return neighbors;
    }
}