package model;

public class PheromoneDrop {
    private int level;

    public PheromoneDrop(int level) {
        this.level = level;
    }

    public void decreaseLevel() {
        // Disminuye la intensidad del rastro
    }

    public int getLevel() {
        return level;
    }
}