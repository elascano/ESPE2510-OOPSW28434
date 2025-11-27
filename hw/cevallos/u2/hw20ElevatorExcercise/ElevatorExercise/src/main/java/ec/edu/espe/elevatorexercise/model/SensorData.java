/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

/**
 *
 * @author Mateo Cevallos
 */
public class SensorData {
    private int sensorType;
    private int location;
    private double value;

    public SensorData(int sensorType, int location, double value) {
        this.sensorType = sensorType;
        this.location = location;
        this.value = value;
    }
}
