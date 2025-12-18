/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class FloorSensor extends Sensor {
    private int currentFloor;

    public FloorSensor(int id) {
        super(id);
        this.currentFloor = 1;
    }

    @Override
    public boolean read() {
        return status;
    }

    public int getCurrentFloor() { return currentFloor; }
    public void setCurrentFloor(int floor) { this.currentFloor = floor; }
}

