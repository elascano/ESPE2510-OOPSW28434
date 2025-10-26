/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author ADRIAN TOAPANTA
 */

public class Pheromone {
    private double strength;
    private static final double EVAPORATION_RATE = 0.1;
    
    public Pheromone(double strength) {
        this.strength = strength;
    }
    
    public double getStrength() {
        return strength;
    }
    
    public void decrease() {
        this.strength = Math.max(0, this.strength - EVAPORATION_RATE);
    }
    
    public boolean isActive() {
        return strength > 0;
    }
}