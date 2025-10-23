package antcolonysimulator.model;

/**
 *
 * @author Arelys Otavalo
 */

import java.util.Random; 

public class Area {
    private int width;
    private int height;
    private Cell[][] grid;
    private Colony[] colonies;
    private int colonyCount;
    private AntEater[] antEaters;
    private int eaterCount;
    private Random random = new Random(); 

    public Area(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
        this.colonies = new Colony[5];
        this.colonyCount = 0;
        this.antEaters = new AntEater[5];
        this.eaterCount = 0;
        setup();
    }

    private void setup() {
        for (int row = 0; row < height; row++)
            for (int col = 0; col < width; col++)
                grid[row][col] = new Cell(row, col, this);
    }

    public void addColony(Colony colony) {
        if (colonyCount < colonies.length)
            colonies[colonyCount++] = colony;
    }

    public void addAntEater(AntEater eater) {
        if (eaterCount < antEaters.length)
            antEaters[eaterCount++] = eater;
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width)
            return null;
        return grid[row][col];
    }
    
    private void trySpawnFood() {
        if (random.nextDouble() < 0.05) { 
            int row = random.nextInt(height);
            int col = random.nextInt(width);
            Cell cell = grid[row][col];
            
            if (cell.getFoodPile() == null) {
                int foodAmount = random.nextInt(41) + 10; 
                cell.setFoodPile(new FoodPile(cell, foodAmount));
                System.out.println(" New food (+" + foodAmount + ") appeared in (" + row + ", " + col + ")");
            }
        }
    }

    public int getTotalFoodInNests() {
        int totalFood = 0;
        for (int i = 0; i < colonyCount; i++) {
            if (colonies[i] != null) {
                totalFood += colonies[i].getNest().totalFood();
            }
        }
        return totalFood;
    }

    public int getTotalPheromoneLevel() {
        int totalPheromone = 0;
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                PheromoneDrop p = grid[i][j].getPheromone();
                if (p != null) {
                    totalPheromone += p.getCurrentLevel();
                }
            }
        return totalPheromone;
    }

    public void runTick() {
        for (int i = 0; i < colonyCount; i++)
            if (colonies[i] != null) colonies[i].run();

        for (int i = 0; i < eaterCount; i++)
            if (antEaters[i] != null) antEaters[i].run();

        trySpawnFood();

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                grid[i][j].decreasePheromone();


        System.out.println("----- State of the area -----");
        System.out.println("Active colonies: " + colonyCount);
        System.out.println("Anteaters: " + eaterCount);
        System.out.println("Total ants: " + getTotalAnts());
        System.out.println("Food in nests: " + getTotalFoodInNests()); 
        System.out.println("Pheromone level: " + getTotalPheromoneLevel()); 
        System.out.println("----------------------------");
    }

    public int getTotalAnts() {
        int total = 0;
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                total += grid[i][j].getAntCount();
        return total;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
        