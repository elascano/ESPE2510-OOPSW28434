/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public abstract class Sensor {
    protected int sensorId;
    protected boolean status;

    public Sensor(int sensorId) {
        this.sensorId = sensorId;
        this.status = true;
    }

    public int getSensorId() { return sensorId; }
    public boolean isActive() { return status; }
    public void activate() { status = true; }
    public void deactivate() { status = false; }

    public abstract boolean read();
}

