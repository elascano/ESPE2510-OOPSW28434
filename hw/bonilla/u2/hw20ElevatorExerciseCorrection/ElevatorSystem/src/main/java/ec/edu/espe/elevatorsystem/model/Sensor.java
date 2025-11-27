package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Sensor {

    private double value;

    public Sensor() {
        this.value = 0.0;
    }

    public int readData() {
        System.out.println("Sensor: Reading data");
        return (int) value;
    }

    public double getSpeed(Motor m, Direction d) {
        System.out.println("Sensor: Getting speed for direction " + d);
        return 1.5;
    }

    public double getValue() {
        return value;
    }
}
