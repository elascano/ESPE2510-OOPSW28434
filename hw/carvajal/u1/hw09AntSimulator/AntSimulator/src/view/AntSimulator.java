package view;

import model.*;

public class AntSimulator {
    public static void main(String[] args) {
        System.out.println("=== Simulacion de Hormigas y Osos Hormigueros ===\n");

        // Crear el área
        Area area = new Area(10, 10);

        // Crear una celda base
        Cell cell = new Cell(5, 5);

        // Crear un nido y su colonia
        Nest nest = new Nest(cell);
        Colony colony = new Colony(nest);

        // Crear una hormiga inicial
        Ant ant = new Ant(cell, colony);
        colony.addAnt(ant);

        // Crear un oso hormiguero
        AntEater eater = new AntEater(cell);

        // Simulación básica de ticks
        for (int tick = 1; tick <= 10; tick++) {
            System.out.println("\n--- Tick " + tick + " ---");

            // Ejecutar las acciones
            ant.run();
            eater.run();

            // Simular reducción de feromonas, envejecimiento, etc.
            // (solo texto para el prototipo)
            System.out.println("[Sistema] Actualizacion del entorno completada.");
        }

        System.out.println("\n=== Fin de la simulacion ===");
    }
}