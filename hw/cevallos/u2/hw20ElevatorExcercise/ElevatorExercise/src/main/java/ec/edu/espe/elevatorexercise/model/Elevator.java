/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

import java.util.List;

/**
 *
 * @author Mateo Cevallos
 */
public class Elevator implements Movement{
    private int position;
    private boolean shaft;
    private int direction;
    private int id;
    private int maxCapacity;
    private double maxWeight;
    private double currentWeight;
    private MotorSystem motorSystem;
    private Cage cage;
    private List<Sensor> sensors;
    private List<Brake> brakes;

    public Elevator(int position, boolean shaft, int direction, int id, int maxCapacity, double maxWeight, double currentWeight, MotorSystem motorSystem, Cage cage, List<Sensor> sensors, List<Brake> brakes) {
        this.position = position;
        this.shaft = shaft;
        this.direction = direction;
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.maxWeight = maxWeight;
        this.currentWeight = currentWeight;
        this.motorSystem = motorSystem;
        this.cage = cage;
        this.sensors = sensors;
        this.brakes = brakes;
    }
    

    public void moveToFloor(int floor) {
        System.out.println("Moving to floor " + floor);
    }

    public void openDoors() {
        System.out.println("Doors opening...");
    }

    public void closeDoors() {
        System.out.println("Doors closing...");
    }

    public boolean checkWeight() {
        return currentWeight <= maxWeight;
    }

    public boolean checkCapacity() {
        return cage.getPersonCount() <= maxCapacity;
    }

    @Override
    public void moveUp() {
        System.out.println("Moving up...");
    }

    @Override
    public void moveDown() {
        System.out.println("Moving down...");
    }

    @Override
    public void stop() {
        System.out.println("Elevator stopped...");
    }
    
    public void setPosition(int position) {
        this.position = position;
        System.out.println("Position set to: " + position);
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        System.out.println("Max capacity set to: " + maxCapacity);
    }
    
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
        System.out.println("Max weight set to: " + maxWeight);
    }

    public int getId() {
        return id;
    }
}
