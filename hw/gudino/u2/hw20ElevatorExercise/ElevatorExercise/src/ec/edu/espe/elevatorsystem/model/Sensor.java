package ec.edu.espe.elevatorsystem.model;

public abstract class Sensor {
    protected double value;
    public abstract float read();
    public double getValue() { return value; }
}
