package model;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
public class FoodPile {
    private int pileAmount; // in mg
    private Cell position;

    public FoodPile(int pileAmount, Cell position) {
        this.pileAmount = pileAmount;
        this.position = position;
    }

    // Si hay suficiente, devuelve x mg, si no, devuelve lo que quede.
    public Food yieldFood(int requestedMg) {
        if (requestedMg <= 0) return new Food(0);
        int given = Math.min(requestedMg, pileAmount);
        pileAmount -= given;
        return new Food(given);
    }

    public int getPileAmount() { return pileAmount; }
    public void setPileAmount(int pileAmount) { this.pileAmount = pileAmount; }
    public Cell getPosition() { return position; }
    public void setPosition(Cell position) { this.position = position; }

    @Override
    public String toString() {
        return "FoodPile{" + pileAmount + "mg at " + position + "}";
    }
}
