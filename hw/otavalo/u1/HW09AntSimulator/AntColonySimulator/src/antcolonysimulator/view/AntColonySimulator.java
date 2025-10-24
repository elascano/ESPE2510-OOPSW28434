package antcolonysimulator.view;

import antcolonysimulator.model.AntEater;
import antcolonysimulator.model.Area;
import antcolonysimulator.model.Cell;
import antcolonysimulator.model.Colony;
import antcolonysimulator.model.FoodPile;
import antcolonysimulator.model.Nest;
import antcolonysimulator.model.SimulatorDisplay;
import java.util.Random; 

/**
 *
 * @author Arelys Otavalo
 */

public class AntColonySimulator {
    public static void main(String[] args) {
        Area area = new Area(10, 10);
        Random random = new Random(); 
        
        int row1 = random.nextInt(area.getHeight()); 
        int col1 = random.nextInt(area.getWidth());  
        Cell nestCell1 = area.getCell(row1, col1);
        
        Nest nest1 = new Nest(nestCell1);
        Colony colony1 = new Colony(nest1);
        area.addColony(colony1);
        System.out.println("Nest 1 created in (" + row1 + ", " + col1 + ")");


        int row2 = random.nextInt(area.getHeight());
        int col2 = random.nextInt(area.getWidth());
        Cell nestCell2 = area.getCell(row2, col2);
        
        Nest nest2 = new Nest(nestCell2);
        Colony colony2 = new Colony(nest2);
        area.addColony(colony2);
        System.out.println("Nest 2 created in (" + row2 + ", " + col2 + ")");


        int foodRow = random.nextInt(area.getHeight());
        int foodCol = random.nextInt(area.getWidth());
        Cell foodCell1 = area.getCell(foodRow, foodCol);
        foodCell1.setFoodPile(new FoodPile(foodCell1, 10));
        System.out.println("Food created in (" + foodRow + ", " + foodCol + ")");
        
        Cell foodCell2 = area.getCell(random.nextInt(area.getHeight()), random.nextInt(area.getWidth()));
        foodCell2.setFoodPile(new FoodPile(foodCell2, 20));


        AntEater eater = new AntEater(area.getCell(0, 0), area);
        area.addAntEater(eater);

        SimulatorDisplay display = new SimulatorDisplay(area);

        for (int tick = 0; tick < 50; tick++) {
            area.runTick();
            display.showStatus(tick);
        }

        System.out.println("End of the program");
    }
}