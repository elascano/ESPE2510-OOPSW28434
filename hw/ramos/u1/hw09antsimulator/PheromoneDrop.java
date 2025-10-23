/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw09antsimulator;

/**
 *
 * @author Paulo Ramos
 */
class PheromoneDrop {
    int currentLevel;

    public PheromoneDrop() {
        this(100);
    }

    public PheromoneDrop(int level) {
        this.currentLevel = level;
    }

    public void run() {
        if (currentLevel > 0) {
            currentLevel -= 1;
        }
    }

    public boolean isActive() {
        return currentLevel > 0;
    }
}
