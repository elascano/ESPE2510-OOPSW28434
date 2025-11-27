/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

/**
 *
 * @author Mateo Cevallos
 */
public class Sensor {
    private int sensorType;
    private int location;
    private double value;

    public Sensor(int sensorType, int location, double value) {
        this.sensorType = sensorType;
        this.location = location;
        this.value = value;
    }

    public SensorData readData() {
        System.out.println("Reading sensor data...");
        return new SensorData(sensorType, location, value);
    }

    public int getCurrentFloor() {
        return location;
    }

    public int getDirection() {
        return 1; // 1 up, -1 down, 0 stop
    }

    public double getSpeed() {
        return value;
    } 
}
