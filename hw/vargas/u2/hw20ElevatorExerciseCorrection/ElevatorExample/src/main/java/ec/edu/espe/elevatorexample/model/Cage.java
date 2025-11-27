
package ec.edu.espe.elevatorexample.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class Cage {

    private final int capacityPersons = 20;
    private boolean lightOn = true;

    public Cage() {
        System.out.println("  [Cage] Cabin initialized (Capacity: " + capacityPersons + " persons).");
    }


    public int getCapacityPersons() {
        return capacityPersons;
    }

    public boolean isLightOn() {
        return lightOn;
    }

    public void setLightOn(boolean lightOn) {
        this.lightOn = lightOn;
        System.out.println("  [Cage] Light is now " + (lightOn ? "ON" : "OFF") + ".");
    }
}