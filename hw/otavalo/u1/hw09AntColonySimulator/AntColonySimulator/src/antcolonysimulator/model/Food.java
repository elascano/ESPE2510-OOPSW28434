package antcolonysimulator.model;

public class Food {
    private int amount;

    public Food(int amount) {
        this.amount = amount;
    }

    public int getAmount() { return amount; }

    public void decrease(int x) {
        if (x > amount) amount = 0;
        else amount -= x;
    }

    public boolean isEmpty() { return amount <= 0; }
}