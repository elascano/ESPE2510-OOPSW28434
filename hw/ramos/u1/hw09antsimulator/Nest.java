/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw09antsimulator;

/**
 *
 * @author Paulo Ramos
 */
class Nest {
    Cell position;
    int stockPile; // mg

    public Nest(Cell position) {
        this.position = position;
        stockPile = 0;
    }

    public void addFood(Food food) {
        if (food != null) stockPile += food.amount;
    }

    public boolean canSpawnAnt() {
        return stockPile >= 5;
    }

    public Ant spawnAnt() {
        if (canSpawnAnt()) {
            stockPile -= 5;
            return new Ant(position);
        }
        return null;
    }
}
