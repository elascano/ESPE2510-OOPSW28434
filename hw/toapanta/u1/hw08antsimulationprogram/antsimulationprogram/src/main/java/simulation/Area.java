/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulation;

/**
 *
 * @author ADRIAN TOAPANTA
 */


import entities.*;

import entities.Ant;

public class Area {
    private int width, height;
    private Cell[][] cells;
    
    public Area(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];
        initializeCells();
    }
    
    private void initializeCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }
    
    public Cell getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return cells[x][y];
        }
        return null;
    }
    
    public void placeFood(int x, int y, double amount) {
        Cell cell = getCell(x, y);
        if (cell != null) {
            cell.setFood(new Food(amount));
        }
    }
    
    public void placeAnt(int x, int y, Ant ant) {
        Cell cell = getCell(x, y);
        if (cell != null) {
            cell.setAnt(ant);
            ant.setPosition(x, y);
        }
    }
    
    public void placeAntEater(int x, int y, AntEater antEater) {
        Cell cell = getCell(x, y);
        if (cell != null) {
            cell.setAntEater(antEater);
        }
    }
    
    public void placeNest(int x, int y, Nest nest) {
        Cell cell = getCell(x, y);
        if (cell != null) {
            cell.setNest(nest);
        }
    }
    
    // Getters
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Cell[][] getCells() { return cells; }
}