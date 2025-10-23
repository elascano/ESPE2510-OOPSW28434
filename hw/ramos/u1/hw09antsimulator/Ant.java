/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw09antsimulator;

/**
 *
 * @author Paulo Ramos
 */
class Ant {
    Cell position;
    int weight;
    Food carrying;

    public Ant(Cell position) {
        this(position, 1);
    }

    public Ant(Cell position, int weight) {
        this.position = position;
        this.weight = weight;
        this.carrying = null;
        position.ants.add(this);
    }

    public void run() {
        if (carrying != null) {
            dropPheromone();
            moveTowardNest();
        } else {
            searchForFood();
        }
    }

    public void dropPheromone() {
        PheromoneDrop pher = new PheromoneDrop();
        position.receivePheromone(pher);
    }

    public void moveTowardNest() {
        // Placeholder
    }

    public void searchForFood() {
        if (position.foodPile != null && carrying == null) {
            Food food = position.foodPile.yieldFood(1);
            if (food != null) {
                carrying = food;
                System.out.println("Ant picked up " + food);
            }
        }
    }

    @Override
    public String toString() {
        return "Ant(weight=" + weight + ", carrying=" + carrying + ")";
    }
}

