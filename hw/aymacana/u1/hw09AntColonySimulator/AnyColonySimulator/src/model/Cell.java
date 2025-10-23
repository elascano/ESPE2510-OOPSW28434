package model;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
import java.util.ArrayList;
import java.util.List;

public class Cell {

    private int row;
    private int col;

    private final List<Ant> ants;
    private FoodPile foodPile;
    private PheromoneDrop pheromone;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.ants = new ArrayList<>();
        this.foodPile = null;
        this.pheromone = null;
    }

    public void receivePheromone(PheromoneDrop newP) {
        if (newP == null) {
            return;
        }
        if (pheromone == null) {
            pheromone = newP;
        } else {
            pheromone.setLevel(Math.min(100, pheromone.getLevel() + newP.getLevel()));
        }
    }

    public List<Ant> getAnts() {
        return ants;
    }

    public void addAnt(Ant a) {
        if (a != null && !ants.contains(a)) {
            ants.add(a);
        }
    }

    public void removeAnt(Ant a) {
        ants.remove(a);
    }

    public FoodPile getFoodPile() {
        return foodPile;
    }

    public void setFoodPile(FoodPile foodPile) {
        this.foodPile = foodPile;
    }

    public PheromoneDrop getPheromone() {
        return pheromone;
    }

    public void setPheromone(PheromoneDrop pheromone) {
        this.pheromone = pheromone;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Cell(" + row + "," + col + ")";
    }
}
