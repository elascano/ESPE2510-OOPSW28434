package view;



import model.Area;
import model.Cell;
import model.Colony;
import model.Simulator;

public class SimulatorDisplay {
    public SimulatorDisplay() {}

    // print a small summary of the area
    public void printSummary(Simulator sim) {
        System.out.println("Tick: " + sim.getCurrentTick());
        Area area = sim.getArea();
        if (area == null) {
            System.out.println("No area defined.");
            return;
        }
        System.out.println("Area: " + area.getWidth() + " x " + area.getHeight());
        int totalAnts = 0;
        for (Colony c : area.getColonies()) totalAnts += c.getAnts().size();
        System.out.println("Colonies: " + area.getColonies().size() + "  Total ants: " + totalAnts);
        System.out.println("AntEaters: " + area.getAntEaters().size());

        // print small grid overview (counts) for small areas
        if (area.getWidth() <= 30 && area.getHeight() <= 15) {
            for (int r = 0; r < area.getHeight(); r++) {
                for (int c = 0; c < area.getWidth(); c++) {
                    Cell cell = area.getCell(r, c);
                    int ants = cell.getAnts().size();
                    int ae = cell.getAntEaters().size();
                    boolean hasFood = cell.hasFood();
                    boolean hasPher = cell.getPheromone() != null;
                    char ch = '.';
                    if (ae > 0) ch = 'E';
                    else if (ants > 0) ch = 'a';
                    else if (hasFood) ch = 'F';
                    else if (hasPher) ch = 'p';
                    System.out.print(ch);
                }
                System.out.println();
            }
        }
        System.out.println("--------------------------------");
    }
}
    

