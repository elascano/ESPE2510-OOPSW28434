/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw09antsimulator;

/**
 *
 * @author Paulo Ramos
 */

class FoodPile {
    Cell position;
    int pileAmount;

    public FoodPile(Cell position, int amount) {
        this.position = position;
        this.pileAmount = amount;
        position.foodPile = this;
    }

    public Food yieldFood(int amount) {
        if (pileAmount <= 0) return null;
        if (pileAmount >= amount) {
            pileAmount -= amount;
            return new Food(amount);
        } else {
            int taken = pileAmount;
            pileAmount = 0;
            return new Food(taken);
        }
    }

    public boolean isEmpty() {
        return pileAmount <= 0;
    }
}
