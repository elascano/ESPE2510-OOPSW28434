package model;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
public class PheromoneDrop {

    private int level;

    public PheromoneDrop(int level) {
        this.level = Math.max(0, Math.min(100, level));
    }

    public void tick() {
        if (level > 0) {
            level = level - 1;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Math.max(0, Math.min(100, level));
    }

    @Override
    public String toString() {
        return "Pheromone(" + level + ")";
    }
}
