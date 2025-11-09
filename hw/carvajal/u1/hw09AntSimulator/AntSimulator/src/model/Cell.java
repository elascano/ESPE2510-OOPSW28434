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
    }

    public void addAnt(Ant ant) {}
    public void addAntEater(AntEater antEater) {}
    public void setFoodPile(FoodPile foodPile) {}
    public void setPheromone(PheromoneDrop pheromone) {}
    
    @Override
public String toString() {
    return x + "," + y;
}
}