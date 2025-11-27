
package ec.edu.espe.elevatorexample.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class WeightSensor implements Sensor {

    private final double limitLbs = 4000.0;
    private double currentValue = 0.0;

    public WeightSensor() {
        System.out.println("  [WeightSensor] Initialized with limit: " + limitLbs + " lbs.");
    }


    public void setCurrentValue(double weight) {
        this.currentValue = weight;
    }


    @Override
    public double readValue() {
        return currentValue;
    }

    @Override
    public String obtainStatus() {
        if (currentValue > limitLbs) {
            return "OVERLOAD";
        } else {
            return "OK";
        }
    }
}
