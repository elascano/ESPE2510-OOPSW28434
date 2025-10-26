/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author ADRIAN TOAPANTA
 */

public class Colony {
    private String name;
    private int population;
    
    public Colony(String name, int population) {
        this.name = name;
        this.population = population;
    }
    
    // Getters y setters
    public String getName() { return name; }
    public int getPopulation() { return population; }
    public void setPopulation(int population) { this.population = population; }
}
