package model;

public class PheromoneDrop {
    private int level;

    public PheromoneDrop(int level) {
        this.level = level;
        // Set the initial intensity level of the pheromone
    }

    // Decrease the intensity of the pheromone
    public void decreaseLevel() {}

    // Get the current level of the pheromone
    public int getLevel() {
        return level;
    }
}