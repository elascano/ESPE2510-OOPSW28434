package antcolonysimulator.model;

/**
 *
 * @author Arelys Otavalo
 */

public class Cell {
    private int row;
    private int col;
    private PheromoneDrop pheromone;
    private FoodPile foodPile;
    private Ant[] ants;
    private int antCount;
    private Area area; 

    public Cell(int row, int col, Area area) { 
        this.row = row;
        this.col = col;
        this.area = area; 
        this.ants = new Ant[10];
        this.antCount = 0;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public Area getArea() { return area; } 

    public void receivePheromone(PheromoneDrop newPheromone) {
        if (pheromone == null) pheromone = newPheromone;
        else pheromone.addLevel(newPheromone.getCurrentLevel());
    }

    public void decreasePheromone() {
        if (pheromone != null) {
            pheromone.decrease();
            if (pheromone.getCurrentLevel() <= 0) pheromone = null;
        }
    }

    public PheromoneDrop getPheromone() { return pheromone; }

    public void addAnt(Ant ant) {
        if (antCount < ants.length) {
            ants[antCount] = ant;
            antCount++;
        }
    }

    public void removeAnt(Ant ant) {
        for (int i = 0; i < antCount; i++) {
            if (ants[i] == ant) {
                for (int j = i; j < antCount - 1; j++)
                    ants[j] = ants[j + 1];
                ants[antCount - 1] = null;
                antCount--;
                break;
            }
        }
    }
    
    public int getAntCount() {
        return antCount;
    }

    public Ant[] getAnts() { return ants; }

    public FoodPile getFoodPile() { return foodPile; }

    public void setFoodPile(FoodPile foodPile) { this.foodPile = foodPile; }
}