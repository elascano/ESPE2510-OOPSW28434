package antcolonysimulator.model;

/**
 *
 * @author Arelys Otavalo
 */

import java.util.Random;

public class AntEater {
    private Cell position;
    private int antsEaten;
    private int ticks;
    private int state; 
    private Area area;
    private Random random = new Random();

    public AntEater(Cell position, Area area) {
        this.position = position;
        this.area = area;
        this.state = 0;
    }

    public void run() {
        if (state == 0) wander();
        else if (state == 1) eat();
        else sleep();
    }

    private void wander() {
        if (position.getAntCount() > 0) {
            
            int count = position.getAntCount();
            Ant[] ants = position.getAnts();
            
            while (position.getAntCount() > 0) {
                position.removeAnt(ants[0]);
            }

            antsEaten += count; 
            state = 1;
            ticks = 0;
        } else moveRandomly();
    }

    private void eat() {
        ticks++;
        if (ticks >= 10) {
            if (antsEaten >= 50) {
                state = 2;
                ticks = 0;
                antsEaten = 0;
            } else state = 0;
        }
    }

    private void sleep() {
        ticks++;
        if (ticks >= 600) state = 0;
    }

    private void moveRandomly() {
        int newRow = position.getRow() + random.nextInt(3) - 1;
        int newCol = position.getCol() + random.nextInt(3) - 1;
        Cell next = area.getCell(newRow, newCol);
        if (next != null) position = next;
    }
}