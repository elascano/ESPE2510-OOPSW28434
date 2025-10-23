package model;

public class Food {
    private int amount;

    public Food(int amount) {
        this.amount = amount;
        // Set the initial amount of food
    }

    // Get the current amount of food
    public int getAmount() {
        return amount;
    }  
}