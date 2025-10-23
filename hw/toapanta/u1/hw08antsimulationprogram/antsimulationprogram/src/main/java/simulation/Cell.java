/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulation;

/**
 *
 * @author ADRIAN TOAPANTA
 */


import entities.*;


import entities.Ant;

public class Cell {
    private int x, y;
    private Food food;
    private Pheromone pheromone;
    private Ant ant;
    private AntEater antEater;
    private Nest nest;
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // Getters y setters
    public int getX() { return x; }
    public int getY() { return y; }
    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }
    public Pheromone getPheromone() { return pheromone; }
    public void setPheromone(Pheromone pheromone) { this.pheromone = pheromone; }
    public Ant getAnt() { return ant; }
    public void setAnt(Ant ant) { this.ant = ant; }
    public AntEater getAntEater() { return antEater; }
    public void setAntEater(AntEater antEater) { this.antEater = antEater; }
    public Nest getNest() { return nest; }
    public void setNest(Nest nest) { this.nest = nest; }
    
    public boolean isEmpty() {
        return food == null && ant == null && antEater == null && nest == null;
    }
    
    public boolean hasFood() {
        return food != null && !food.isEmpty();
    }
}