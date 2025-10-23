/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Daniel
 */

public class FoodPile {
    private int amount; // mg

    public FoodPile() { this.amount = 0; }
    public FoodPile(int amount) { this.amount = Math.max(0, amount); }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = Math.max(0, amount); }

    // Request x mg from pile; returns Food object with what was taken.
    public Food yieldFood(int x) {
        if (x <= 0) return new Food(0);
        int taken = Math.min(amount, x);
        amount -= taken;
        return new Food(taken);
    }

    public boolean isEmpty() { return amount <= 0; }
}