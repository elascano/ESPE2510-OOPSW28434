/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
import java.util.Random;

public class MovementSensor extends Sensor {

    private Random random = new Random();

    public MovementSensor(int id) {
        super(id);
    }

    @Override
    public boolean read() {
        // generic read, true means working
        return status;
    }

    public String readMovementDirection() {
        return random.nextBoolean() ? "ELEVATOR MOVING UP..." : "ELEVATOR MOVING DOWN...";
    }
}

