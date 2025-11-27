package ec.edu.espe.elevator.model;

/**
 *
 * @author Paulo Ramos
 */
public abstract class Sensor {
    
    private int maxWeight;

    public Sensor(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    
    /**
     * @return the maxWeight
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * @param maxWeight the maxWeight to set
     */
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
    
    
            
    
}
