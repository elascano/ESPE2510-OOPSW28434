
package model;


public class PheromoneDrop {
    private int level; // 0..100

    public PheromoneDrop() { this.level = 100; }
    public PheromoneDrop(int level) { this.level = Math.max(0, Math.min(100, level)); }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = Math.max(0, Math.min(100, level)); }

    public void decrease() {
        if (level > 0) level--;
    }

    public void increase(int amount) {
        setLevel(level + amount);
    }

    public boolean isAlive() { return level > 0; }
}