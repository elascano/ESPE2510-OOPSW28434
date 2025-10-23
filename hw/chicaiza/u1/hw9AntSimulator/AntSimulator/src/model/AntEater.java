package model;

public class AntEater {
    private Cell position;
    private int antsEaten;
    private boolean sleeping;
    private int ticksSinceLastMeal;

    public AntEater(Cell start) {
        this.position = start;
        this.sleeping = false;
        this.antsEaten = 0;
    }

    public void run() {
        System.out.println("[AntEater] In the current tick, the anteater at (" 
            + position + ") acts...");

        if (sleeping) {
            System.out.println("   - The anteater is still sleeping...");
            ticksSinceLastMeal++;
            if (ticksSinceLastMeal > 5) {
                sleeping = false;
                System.out.println("   - It wakes up feeling hungry.");
            }
        } else {
            System.out.println("   - The anteater searches for ants.");
            antsEaten += 5;
            System.out.println("   - It eats some ants (total eaten: " + antsEaten + ").");

            if (antsEaten >= 50) {
                sleeping = true;
                ticksSinceLastMeal = 0;
                System.out.println("   - It has eaten too much and goes to sleep.");
            }
        }

        System.out.println("   - Ends its turn.\n");
    }
}