/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmsimulator.model;

/**
 *
 * @author Josue Rojas
 * @version 0.1
 */
public class Poop {
    private int amount;

    @Override
    public String toString() {
        return "Poop{" + "amount=" + amount + '}';
    }

    
    
    public Poop(int amount) {
        this.amount = amount;
    }

    
    
    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }
  
}