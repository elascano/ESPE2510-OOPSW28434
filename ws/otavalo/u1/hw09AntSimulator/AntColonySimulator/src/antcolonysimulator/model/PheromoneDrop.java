package antcolonysimulator.model;

/**
 *
 * @author Arelys Otavalo
 */

public class PheromoneDrop {
    private int currentLevel;

    public PheromoneDrop(int initialLevel) {
        this.currentLevel = initialLevel;
    }

    public int getCurrentLevel() { return currentLevel; }

    public void decrease() { currentLevel--; }

    public void addLevel(int amount) { currentLevel += amount; }
}