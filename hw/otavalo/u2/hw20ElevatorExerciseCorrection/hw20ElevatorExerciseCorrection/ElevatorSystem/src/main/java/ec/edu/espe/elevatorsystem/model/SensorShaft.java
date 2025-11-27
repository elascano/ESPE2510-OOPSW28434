package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class SensorShaft extends Sensor {
    public float detectedSpeed() {
        System.out.println("Detecting shaft speed...");
        return 0.0f;
    }

    public int detectLocation() {
        System.out.println("Detecting cage location in shaft...");
        return 1;
    }

    public boolean detectDirection() {
        System.out.println("Detecting movement direction...");
        return true;
    }
}
