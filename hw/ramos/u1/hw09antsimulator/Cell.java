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

class Cell {
    int row, col;
    List<PheromoneDrop> pheromones;
    List<Ant> ants;
    FoodPile foodPile;
    List<AntEater> antEaters;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        pheromones = new ArrayList<>();
        ants = new ArrayList<>();
        antEaters = new ArrayList<>();
        foodPile = null;
    }

    public void receivePheromone(PheromoneDrop pheromone) {
        pheromones.add(pheromone);
    }

    public List<Ant> getAnts() {
        return ants;
    }

    @Override
    public String toString() {
        return "Cell(" + row + "," + col + ")";
    }
}
