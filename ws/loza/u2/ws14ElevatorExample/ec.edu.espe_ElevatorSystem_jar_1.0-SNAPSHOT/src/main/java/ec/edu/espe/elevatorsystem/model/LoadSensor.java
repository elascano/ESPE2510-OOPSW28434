/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class LoadSensor extends Sensor {
    private double currentLoadKg;

    public LoadSensor(int id) {
        super(id);
        this.currentLoadKg = 0.0;
    }

    @Override
    public boolean read() {
        // returns true if active sensor; business checks done externally
        return status;
    }

    public void setCurrentLoadKg(double kg) { this.currentLoadKg = kg; }
    public double getCurrentLoadKg() { return currentLoadKg; }
}

