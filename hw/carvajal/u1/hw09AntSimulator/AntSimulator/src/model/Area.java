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
        // Inicialización del grid y listas
    }

    public void runTick() {
        // Aquí se actualizarán todas las entidades
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
}