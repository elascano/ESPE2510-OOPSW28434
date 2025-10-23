package model;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
public class Food {
    private int amount; // in mg

    public Food(int amount) {
        this.amount = amount;
    }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "Food{" + amount + "mg}";
    }
}
