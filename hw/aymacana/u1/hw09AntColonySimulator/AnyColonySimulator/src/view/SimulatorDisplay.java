package view;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
import model.Area;
import model.Colony;
import model.AntEater;
import model.Nest;
import model.Ant;
import model.Cell;

public class SimulatorDisplay {

    public void showHeader(Area area) {
        System.out.println("=== Ant Simulation ===");
        System.out.println(area);
    }

    public void render(Area area) {
        System.out.println("Tick: " + area.getGlobalTick());

        for (Colony c : area.getColonies()) {
            Nest n = c.getNest();
            System.out.println("  " + c);
            System.out.println("    Nest: " + n);
            System.out.println("    Ants: " + c.getAnts().size());

            int showCount = Math.min(3, c.getAnts().size());
            for (int i = 0; i < showCount; i++) {
                Ant a = c.getAnts().get(i);
                System.out.println("      " + a.toString());
            }

            if (c.getAnts().size() > 3) {
                System.out.println("      ... y " + (c.getAnts().size() - 3) + " hormigas más");
            }
        }

        for (AntEater ae : area.getAntEaters()) {
            System.out.println("  " + ae);
        }

        System.out.println("--- Pheromones ---");
        int pheromoneCells = countPheromoneCells(area);
        System.out.println("Cells with pheromones: " + pheromoneCells);

        showTopPheromones(area, 5);

        System.out.println("--- Summary ---");
        if (!area.getColonies().isEmpty() && !area.getColonies().get(0).getAnts().isEmpty()) {
            Ant firstAnt = area.getColonies().get(0).getAnts().get(0);
            System.out.println("Ants - Weight: " + firstAnt.getWeight() + "mg, Charging: " + firstAnt.getCarrying() + "mg");
        }
        System.out.println("Piles of food: " + countActiveFoodPiles(area));
        System.out.println("--------------------------------");
    }

    private int countPheromoneCells(Area area) {
        int count = 0;
        for (int r = 0; r < area.getHeight(); r++) {
            for (int c = 0; c < area.getWidth(); c++) {
                Cell cell = area.getCell(r, c);
                if (cell != null && cell.getPheromone() != null && cell.getPheromone().getLevel() > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private void showTopPheromones(Area area, int maxToShow) {
        java.util.List<String> topPheromones = new java.util.ArrayList<>();

        for (int r = 0; r < area.getHeight(); r++) {
            for (int c = 0; c < area.getWidth(); c++) {
                Cell cell = area.getCell(r, c);
                if (cell != null && cell.getPheromone() != null && cell.getPheromone().getLevel() > 0) {
                    String info = "Pheromone[" + cell + "]: level=" + cell.getPheromone().getLevel();
                    topPheromones.add(info);
                }
            }
        }

        topPheromones.sort((a, b) -> {
            int levelA = Integer.parseInt(a.split("level=")[1]);
            int levelB = Integer.parseInt(b.split("level=")[1]);
            return Integer.compare(levelB, levelA);
        });

        int showCount = Math.min(maxToShow, topPheromones.size());
        for (int i = 0; i < showCount; i++) {
            System.out.println("    " + topPheromones.get(i));
        }

        if (topPheromones.size() > maxToShow) {
            System.out.println("    ... and " + (topPheromones.size() - maxToShow) + " more pheromones");
        } else if (topPheromones.isEmpty()) {
            System.out.println("    There are no active pheromones");
        }
    }

    private int countActiveFoodPiles(Area area) {
        int count = 0;
        try {
            for (Object fp : area.getFoodPiles()) {
                java.lang.reflect.Method method = fp.getClass().getMethod("getPileAmount");
                int amount = (int) method.invoke(fp);
                if (amount > 0) {
                    count++;
                }
            }
        } catch (Exception e) {
            count = area.getFoodPiles().size();
        }
        return count;
    }
}
