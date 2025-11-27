
package ec.edu.espe.elevatorsystem.model;

public class MovementSensor extends Sensor {
    @Override
    public float read() {
        return (float) value;
    }
}

