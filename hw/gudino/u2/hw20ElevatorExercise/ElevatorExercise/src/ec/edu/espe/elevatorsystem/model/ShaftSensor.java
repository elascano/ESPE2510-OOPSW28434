
package ec.edu.espe.elevatorsystem.model;

public class ShaftSensor extends Sensor {
    @Override
    public float read() {
        return (float) value;
    }
}
