
package ec.edu.espe.elevatorsystem.model;

public class DoorSensor extends Sensor {
    @Override
    public float read() {
        // ejemplo simple
        return (float) value;
    }
}

