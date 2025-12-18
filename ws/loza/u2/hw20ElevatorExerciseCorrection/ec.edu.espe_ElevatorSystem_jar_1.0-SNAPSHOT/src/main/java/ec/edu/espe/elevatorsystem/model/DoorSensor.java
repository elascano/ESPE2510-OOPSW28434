/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class DoorSensor extends Sensor {
    private boolean doorClosed;

    public DoorSensor(int id) {
        super(id);
        this.doorClosed = true;
    }

    @Override
    public boolean read() {
        return doorClosed;
    }

    public boolean isDoorClosed() { return doorClosed; }
    public void setDoorClosed(boolean closed) { this.doorClosed = closed; }
}

