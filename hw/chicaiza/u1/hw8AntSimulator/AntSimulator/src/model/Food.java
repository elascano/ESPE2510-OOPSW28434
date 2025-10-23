/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



public class Food {
    private int amount; // in mg

    public Food() { this.amount = 0; }
    public Food(int amount) { this.amount = Math.max(0, amount); }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = Math.max(0, amount); }
}