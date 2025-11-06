/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.ChickenFarmSimulator.model;

/**
 *
 * @author Paulo Ramos
 */
public class Poop {
    // Atributo privado
    private int amount;

    // Constructor
    public Poop(int amount) {
        this.amount = amount;
    }

    // Getter
    public int getAmount() {
        return amount;
    }

    // Setter
    public void setAmount(int amount) {
        this.amount = amount;
    }

    // Método toString sobrescrito
    @Override
    public String toString() {
        return "Poop{amount=" + amount + "}";
    }
}


