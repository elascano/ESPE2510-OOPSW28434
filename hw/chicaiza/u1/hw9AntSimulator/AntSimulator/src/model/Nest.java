package model;

public class Nest {
    private Cell location;
    private int foodAmount;

    public Nest(Cell location) {
        this.location = location;
        this.foodAmount = 0;
        // Initialize the nest at a specific cell with no food
    }

    // Add food to the nest
    public void addFood(int mg) {}

    // Create a new ant in the nest
    public void createAnt() {}
}