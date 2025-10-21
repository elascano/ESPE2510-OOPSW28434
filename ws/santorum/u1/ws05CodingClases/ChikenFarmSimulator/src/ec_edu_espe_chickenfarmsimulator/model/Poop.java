/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec_edu_espe_chickenfarmsimulator.model;



/**
 *
 * @author LABS-ESPE
 */
public class Poop {
        private int amount;

    public Poop(int amount){
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Poop{" + "amount=" + amount + '}';
    }
    



    }
    
