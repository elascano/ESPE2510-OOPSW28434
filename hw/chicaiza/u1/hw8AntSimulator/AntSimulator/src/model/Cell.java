
package model;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int row;
    private int col;
    private FoodPile foodPile;
    private PheromoneDrop pheromone;
    private List<Ant> ants;
    private List<AntEater> antEaters;

    public Cell() {
        this.ants = new ArrayList<>();
        this.antEaters = new ArrayList<>();
    }

    public Cell(int row, int col) {
        this();
        this.row = row;
        this.col = col;
    }

    // getters & setters
    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }

    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }

    public FoodPile getFoodPile() { return foodPile; }
    public void setFoodPile(FoodPile foodPile) { this.foodPile = foodPile; }

    public PheromoneDrop getPheromone() { return pheromone; }
    public void setPheromone(PheromoneDrop pheromone) { this.pheromone = pheromone; }

    public List<Ant> getAnts() { return ants; }
    public List<AntEater> getAntEaters() { return antEaters; }

    public void addAnt(Ant a) {
        if (a != null && !ants.contains(a)) ants.add(a);
    }

    public void removeAnt(Ant a) { ants.remove(a); }

    public void addAntEater(AntEater ae) {
        if (ae != null && !antEaters.contains(ae)) antEaters.add(ae);
    }

    public void removeAntEater(AntEater ae) { antEaters.remove(ae); }

    // allow ants to drop pheromone
    public void addPheromone(int amount) {
        if (amount <= 0) return;
        if (pheromone == null) pheromone = new PheromoneDrop(amount);
        else pheromone.increase(amount);
    }

    // utility
    public boolean hasFood() {
        return foodPile != null && foodPile.getAmount() > 0;
    }
}