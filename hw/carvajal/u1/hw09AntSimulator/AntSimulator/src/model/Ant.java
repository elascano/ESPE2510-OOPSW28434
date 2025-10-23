package model;

public class Ant {
    private int weight;
    private boolean carryingFood;
    private Cell position;
    private Colony colony;

    public Ant(Cell start, Colony colony) {
        this.position = start;
        this.colony = colony;
        this.weight = 1;
        this.carryingFood = false;
    }

    public void run() {
        System.out.println("[Ant] En el tick actual, la hormiga en (" 
            + position + ") realiza su accion...");

        if (weight < 3) {
            System.out.println("   - La hormiga todavia esta en el nido comiendo para ganar peso.");
            weight++;
            System.out.println("   - Ahora pesa " + weight + " mg.");
        } else if (!carryingFood) {
            System.out.println("   - La hormiga sale a buscar comida.");
            carryingFood = true;
            System.out.println("   - Encuentra un poco de comida y la recoge.");
        } else {
            System.out.println("   - La hormiga regresa al nido para dejar la comida.");
            carryingFood = false;
            colony.getNest().addFood(1);
        }

        System.out.println("   - La hormiga termina su turno.\n");
    }
}