package model;

import java.util.List;

public class Cell {
    private int x;
    private int y;
    private PheromoneDrop pheromone;
    private FoodPile foodPile;
    private List<Ant> ants;
    private List<AntEater> antEaters;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        // Initialize lists if needed
    }

    // Add an ant to this cell
    public void addAnt(Ant ant) {}

    // Add an anteater to this cell
    public void addAntEater(AntEater antEater) {}

    // Set the food pile on this cell
    public void setFoodPile(FoodPile foodPile) {}

    // Set the pheromone drop on this cell
    public void setPheromone(PheromoneDrop pheromone) {}
    
    @Override
    public String toString() {
        return x + "," + y;
    }
}