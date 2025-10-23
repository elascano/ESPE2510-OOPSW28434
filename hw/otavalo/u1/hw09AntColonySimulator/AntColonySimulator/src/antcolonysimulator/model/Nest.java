package antcolonysimulator.model;

/**
 *
 * @author Arelys Otavalo
 */

public class Nest {
    private Cell position;
    private Food[] foodStock;
    private int foodCount;

    public Nest(Cell position) {
        this.position = position;
        this.foodStock = new Food[20];
        this.foodCount = 0;
    }

    public Cell getPosition() { 
        return position; 
    }

    public void addFood(Food food) {
        if (foodCount < foodStock.length) {
            foodStock[foodCount] = food;
            foodCount++;
        }
    }

    public int totalFood() {
        int total = 0;
        for (int i = 0; i < foodCount; i++) {
            if (foodStock[i] != null) {
                total += foodStock[i].getAmount();
            }
        }
        return total;
    }

    public boolean hasEnoughFood() {
        return totalFood() >= 5;
    }

    public void consumeForNewAnt() {
        if (hasEnoughFood()) {
            int needed = 5;
            for (int i = 0; i < foodCount && needed > 0; i++) {
                if (foodStock[i] != null) {
                    int take = Math.min(foodStock[i].getAmount(), needed);
                    foodStock[i].decrease(take);
                    needed -= take;
                }
            }
        }
    }

    public Area getArea() {
        return position.getArea();
    }

    public int getRow() {
        return position.getRow();
    }

    public int getCol() {
        return position.getCol();
    }
}
