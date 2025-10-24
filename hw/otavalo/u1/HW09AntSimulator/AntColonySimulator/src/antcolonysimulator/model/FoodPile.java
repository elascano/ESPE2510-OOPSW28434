package antcolonysimulator.model;

/**
 *
 * @author Arelys Otavalo
 */

public class FoodPile {
    private int pileAmount;
    private Cell position;

    public FoodPile(Cell position, int pileAmount) {
        this.position = position;
        this.pileAmount = pileAmount;
    }

    public Food yieldFood(int amount) {
        int taken;
            if (pileAmount >= amount) {
                taken = amount; 
            } else {
                taken = pileAmount; 
    }
        pileAmount -= taken;
        return new Food(taken);
    }

    public int getPileAmount() { return pileAmount; }

    public Cell getPosition() { return position; }

    public boolean isEmpty() { return pileAmount <= 0; }
}