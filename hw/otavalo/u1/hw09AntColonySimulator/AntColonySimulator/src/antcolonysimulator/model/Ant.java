package antcolonysimulator.model;

/**
 *
 * @author Arelys Otavalo
 */

import java.util.Random;

public class Ant {
    private Cell position;
    private Food carrying;
    private Area area;
    private Colony colony; 
    private Random random = new Random();

    public Ant(Cell position, Area area, Colony colony) { 
        this.position = position;
        this.area = area;
        this.colony = colony; 
        this.carrying = null;
    }

    public void run() {
        if (carrying != null) {
            
            if (position == colony.getNest().getPosition()) {
                colony.getNest().addFood(carrying);
                System.out.println("Food deposited in the colony's nest.");
                carrying = null;
            } 
            
            dropPheromone();
            moveRandomly();
            
        } else {
            FoodPile fp = position.getFoodPile();
            if (fp != null && !fp.isEmpty()) {
                carrying = fp.yieldFood(1);
            } else {
                moveRandomly();
            }
        }
    }

    private void dropPheromone() {
        position.receivePheromone(new PheromoneDrop(100));
    }

    private void moveRandomly() {
        int newRow = position.getRow() + random.nextInt(3) - 1;
        int newCol = position.getCol() + random.nextInt(3) - 1;
        Cell next = area.getCell(newRow, newCol);

        if (next != null) {
            position.removeAnt(this);
            position = next;
            position.addAnt(this);
        }
    }

    public Cell getPosition() { return position; }
}