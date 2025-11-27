/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

/**
 *
 * @author Mateo Cevallos
 */
public class Floor {
    
    private Floor[] floors = new Floor[20];
    private Elevator[] elevator = new Elevator[3];
    private int floorNumber;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void goUp() {
        System.out.println("Calling elevator to go up...");
    }

    public void goDown() {
        System.out.println("Calling elevator to go down...");
    }

    public Floor[] getFloors() {
        return floors;
    }

    public void setFloors(Floor[] floors) {
        this.floors = floors;
    }

    public Elevator[] getElevator() {
        return elevator;
    }

    public void setElevator(Elevator[] elevator) {
        this.elevator = elevator;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
    
    
    
}
