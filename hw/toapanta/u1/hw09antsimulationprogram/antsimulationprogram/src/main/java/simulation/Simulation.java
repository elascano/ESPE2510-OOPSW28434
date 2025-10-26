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
import enums.Direction;
import java.util.*;


import entities.Ant;

public class Simulation {
    private Area area;
    private List<Ant> ants;
    private AntEater antEater;
    private Time time;
    private Random random;
    
    public Simulation() {
        this.area = new Area(50, 50); // Área de 50x50
        this.ants = new ArrayList<>();
        this.time = new Time();
        this.random = new Random();
        
        initializeSimulation();
    }
    
    private void initializeSimulation() {
        // Crear colonia y nido
        Colony colony = new Colony("Main Colony", 10);
        Nest nest = new Nest(25, 25, colony);
        area.placeNest(25, 25, nest);
        
        // Crear hormigas alrededor del nido
        for (int i = 0; i < 10; i++) {
            int x = 25 + random.nextInt(5) - 2;
            int y = 25 + random.nextInt(5) - 2;
            Ant ant = new Ant(x, y);
            ants.add(ant);
            area.placeAnt(x, y, ant);
        }
        
        // Crear oso hormiguero
        antEater = new AntEater(10, 10);
        area.placeAntEater(10, 10, antEater);
        
        // Colocar comida aleatoria
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(50);
            int y = random.nextInt(50);
            area.placeFood(x, y, 10.0); // 10mg de comida
        }
    }
    
    public void run() {
        System.out.println("Iniciando simulación de hormigas...");
        
        while (true) {
            tick();
            
            // Mostrar estado cada 100 ticks
            if (time.getTicks() % 100 == 0) {
                displayStatus();
            }
            
            try {
                Thread.sleep(100); // 100ms por tick
            } catch (InterruptedException e) {
                System.out.println("Simulación interrumpida");
                break;
            }
        }
    }
    
    private void tick() {
        time.tick();
        
        // Mover hormigas
        for (Ant ant : ants) {
            moveAnt(ant);
        }
        
        // Mover oso hormiguero
        antEater.roam(area);
        updateAntEaterPosition();
        
        // Actualizar feromonas
        updatePheromones();
    }
    
    private void moveAnt(Ant ant) {
        Direction[] directions = Direction.values();
        Direction randomDir = directions[random.nextInt(directions.length)];
        
        int newX = ant.getX(), newY = ant.getY();
        
        switch (randomDir) {
            case NORTH -> newY--;
            case SOUTH -> newY++;
            case EAST -> newX++;
            case WEST -> newX--;
            case NORTHEAST -> { newX++; newY--; }
            case NORTHWEST -> { newX--; newY--; }
            case SOUTHEAST -> { newX++; newY++; }
            case SOUTHWEST -> { newX--; newY++; }
        }
        
        // Verificar límites y mover
        if (newX >= 0 && newX < area.getWidth() && newY >= 0 && newY < area.getHeight()) {
            // Limpiar posición anterior
            area.getCell(ant.getX(), ant.getY()).setAnt(null);
            
            // Mover a nueva posición
            ant.move(randomDir);
            area.getCell(newX, newY).setAnt(ant);
            
            // Verificar si hay comida
            Cell currentCell = area.getCell(newX, newY);
            if (currentCell.hasFood() && !ant.hasFood()) {
                ant.retrieveFood();
                currentCell.getFood().decrease(1.0);
            }
        }
    }
    
    private void updateAntEaterPosition() {
        // Actualizar posición del oso hormiguero en el área
        for (int x = 0; x < area.getWidth(); x++) {
            for (int y = 0; y < area.getHeight(); y++) {
                Cell cell = area.getCell(x, y);
                if (cell.getAntEater() != null) {
                    cell.setAntEater(null);
                }
            }
        }
        area.placeAntEater(antEater.getX(), antEater.getY(), antEater);
    }
    
    private void updatePheromones() {
        // Las hormigas dejan feromonas cuando llevan comida
        for (Ant ant : ants) {
            if (ant.hasFood()) {
                Cell cell = area.getCell(ant.getX(), ant.getY());
                if (cell.getPheromone() == null) {
                    cell.setPheromone(new Pheromone(1.0));
                }
            }
        }
        
        // Evaporar feromonas existentes
        for (int x = 0; x < area.getWidth(); x++) {
            for (int y = 0; y < area.getHeight(); y++) {
                Cell cell = area.getCell(x, y);
                if (cell.getPheromone() != null) {
                    cell.getPheromone().decrease();
                    if (!cell.getPheromone().isActive()) {
                        cell.setPheromone(null);
                    }
                }
            }
        }
    }
    
    private void displayStatus() {
        System.out.println("=== Tick: " + time.getTicks() + " ===");
        System.out.println("Hormigas: " + ants.size());
        System.out.println("Oso hormiguero en: (" + antEater.getX() + ", " + antEater.getY() + ")");
        
        int foodCells = 0;
        int pheromoneCells = 0;
        
        for (int x = 0; x < area.getWidth(); x++) {
            for (int y = 0; y < area.getHeight(); y++) {
                Cell cell = area.getCell(x, y);
                if (cell.hasFood()) foodCells++;
                if (cell.getPheromone() != null) pheromoneCells++;
            }
        }
        
        System.out.println("Celdas con comida: " + foodCells);
        System.out.println("Celdas con feromonas: " + pheromoneCells);
        System.out.println();
    }
}
