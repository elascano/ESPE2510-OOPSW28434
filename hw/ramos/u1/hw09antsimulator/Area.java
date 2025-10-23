/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw09antsimulator;

/**
 *
 * @author Paulo Ramos
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Area {
    int width, height, intNumberOfFoodPiles, tickDuration;
    Cell[][] cells;
    List<Colony> colonies;
    List<AntEater> antEaters;
    Random random = new Random();

    public Area(int width, int height, int intNumberOfFoodPiles, int tickDuration) {
        this.width = width;
        this.height = height;
        this.intNumberOfFoodPiles = intNumberOfFoodPiles;
        this.tickDuration = tickDuration;
        cells = new Cell[height][width];
        colonies = new ArrayList<>();
        antEaters = new ArrayList<>();

        for (int r = 0; r < height; r++)
            for (int c = 0; c < width; c++)
                cells[r][c] = new Cell(r, c);
    }

    public void setup() {
        for (int i = 0; i < intNumberOfFoodPiles; i++) {
            int r = random.nextInt(height);
            int c = random.nextInt(width);
            new FoodPile(cells[r][c], 5 + random.nextInt(16)); // 5-20
        }
    }

    public void addColony(Colony colony) {
        colonies.add(colony);
    }

    public void addAntEater(AntEater eater) {
        antEaters.add(eater);
    }

    public boolean isAnyFoodRemaining() {
        for (int r = 0; r < height; r++)
            for (int c = 0; c < width; c++)
                if (cells[r][c].foodPile != null && !cells[r][c].foodPile.isEmpty())
                    return true;
        return false;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void run() {
        for (Colony colony : colonies) colony.run();
        for (AntEater eater : antEaters) eater.run();
    }
}
