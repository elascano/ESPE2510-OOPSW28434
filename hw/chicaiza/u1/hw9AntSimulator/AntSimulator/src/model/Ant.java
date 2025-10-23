package model;

public class Ant {
    private int weight;
    private boolean carryingFood;
    private Cell position;
    private Colony colony;

    public Ant(Cell start, Colony colony) {
        this.position = start;
        this.colony = colony;
        this.weight = 1;
        this.carryingFood = false;
    }

    public void run() {
        System.out.println("[Ant] In the current tick, the ant at (" 
            + position + ") performs its action...");

        if (weight < 3) {
            System.out.println("   - The ant is still in the nest eating to gain weight.");
            weight++;
            System.out.println("   - It now weighs " + weight + " mg.");
        } else if (!carryingFood) {
            System.out.println("   - The ant goes out to search for food.");
            carryingFood = true;
            System.out.println("   - It finds some food and picks it up.");
        } else {
            System.out.println("   - The ant returns to the nest to drop the food.");
            carryingFood = false;
            colony.getNest().addFood(1);
        }

        System.out.println("   - The ant ends its turn.\n");
    }
}