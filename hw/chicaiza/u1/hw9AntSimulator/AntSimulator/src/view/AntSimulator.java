package view;

import model.*;

public class AntSimulator {
    public static void main(String[] args) {
        System.out.println("=== Ants and Anteaters Simulation ===\n");

        // Create the area
        Area area = new Area(10, 10);

        // Create a base cell
        Cell cell = new Cell(5, 5);

        // Create a nest and its colony
        Nest nest = new Nest(cell);
        Colony colony = new Colony(nest);

        // Create an initial ant
        Ant ant = new Ant(cell, colony);
        colony.addAnt(ant);

        // Create an anteater
        AntEater eater = new AntEater(cell);

        // Basic tick simulation
        for (int tick = 1; tick <= 10; tick++) {
            System.out.println("\n--- Tick " + tick + " ---");

            // Execute actions
            ant.run();
            eater.run();

            // Simulate pheromone reduction, aging, etc.
            // (text only for the prototype)
            System.out.println("[System] Environment update completed.");
        }

        System.out.println("\n=== End of simulation ===");
    }
}