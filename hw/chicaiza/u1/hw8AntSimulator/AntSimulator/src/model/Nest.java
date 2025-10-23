/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Daniel
 */
public class Nest {
    private Cell position;
    private int storedFood; // mg

    public Nest() {}
    public Nest(Cell position) { this.position = position; this.storedFood = 0; }

    public Cell getPosition() { return position; }
    public void setPosition(Cell position) { this.position = position; }

    public int getStoredFood() { return storedFood; }
    public void setStoredFood(int storedFood) { this.storedFood = Math.max(0, storedFood); }

    public void addFood(int mg) {
        if (mg > 0) storedFood += mg;
    }

    // If >=5 mg, spawn an ant (caller should handle adding ant to colony)
    public boolean canSpawnAnt() { return storedFood >= 5; }

    public void consumeForSpawn() {
        if (canSpawnAnt()) storedFood -= 5;
    }
}
