package controller;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
import model.*;
import view.SimulatorDisplay;

public class SimulationController {

    public static void main(String[] args) {

        int width = 12;
        int height = 10;
        int initFoodPiles = 8;
        int tickDurationMs = 100;

        Area area = new Area(width, height, initFoodPiles, tickDurationMs);
        area.setupRandomPiles();

        Cell nestCell = area.getCell(height / 2, width / 2);
        Nest nest = new Nest(nestCell);
        Colony colony = new Colony(nest);
        area.addColony(colony);

        nest.addFood(new Food(10));

        Ant initial = new Ant(nestCell, 1, colony, nest);
        colony.addAnt(initial);
        nestCell.addAnt(initial);

        Cell aePos1 = area.getCell(height / 2, width / 2 + 1);
        AntEater ae1 = new AntEater(aePos1);
        area.addAntEater(ae1);

        Cell aePos2 = area.getCell(height / 2 + 1, width / 2);
        AntEater ae2 = new AntEater(aePos2);
        area.addAntEater(ae2);

        Cell aePos3 = area.getCell(height / 2, width / 2 - 1);
        AntEater ae3 = new AntEater(aePos3);
        area.addAntEater(ae3);

        SimulatorDisplay view = new SimulatorDisplay();
        view.showHeader(area);

        System.out.println("=== INITIALIZATION COMPLETED ===");
        System.out.println(" - Piles of food: " + area.getFoodPiles().size());
        System.out.println(" - Initial nest stock: " + nest.getStockMg() + "mg");
        System.out.println(" - Starting ants: " + colony.getAnts().size());
        System.out.println(" - Anteaters: " + area.getAntEaters().size());
        System.out.println(" - Area: " + width + "x" + height);
        System.out.println("=================================");

        int totalTicks = 600;
        int highStockAlerts = 0;

        for (int t = 0; t < totalTicks; t++) {
            area.update();

            if (nest.getStockMg() > 200) {
                highStockAlerts++;

                if (t % 30 == 0) {
                    System.out.println("\n--- TICK " + t + " ---");
                    view.render(area);

                    System.out.println("--- System Balance ---");
                    System.out.println("Stock nest: " + nest.getStockMg() + "mg");
                    System.out.println("Ants: " + colony.getAnts().size());

                    if (!colony.getAnts().isEmpty()) {
                        int antsEating = 0;
                        int antsCarrying = 0;
                        for (Ant ant : colony.getAnts()) {
                            if (ant.getCarrying() > 0) {
                                antsCarrying++;
                            }
                            if (ant.getWeight() < 3) {
                                antsEating++;
                            }
                        }
                        System.out.println("Ants carrying food: " + antsCarrying);
                        System.out.println("Hungry Antse: " + antsEating);
                    }
                }

                try {
                    Thread.sleep(tickDurationMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("\n" + "=".repeat(60));
            System.out.println("           FINAL SIMULATION SUMMARY");
            System.out.println("=".repeat(60));

            System.out.println("   Final stock: " + nest.getStockMg() + "mg");
            System.out.println("   Final ants: " + colony.getAnts().size());
            System.out.println("   Anteaters: " + area.getAntEaters().size());
            System.out.println("   Completed Tickts " + totalTicks);
        }

    }
}
