
package ec.edu.espe.elevatorsystem.model;

public class WeightSensor extends Sensor {
    @Override
    public float read() {
        return (float) value;
    }
}

