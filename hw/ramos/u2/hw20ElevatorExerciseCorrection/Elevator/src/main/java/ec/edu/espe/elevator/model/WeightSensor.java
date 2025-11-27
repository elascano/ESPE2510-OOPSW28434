package ec.edu.espe.elevator.model;

/**
 *
 * @author Paulo Ramos
 */
public class WeightSensor extends Sensor{

    public WeightSensor(int maxWeight) {
        super(maxWeight);
    }

    public void selectFloor() {
        System.out.println("maximum weight reached");
    }
    
}
