/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmsimulator.model;

/**
 *
 * @author Mateo Cevallos, Object Masters, @ESPE
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

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
}
