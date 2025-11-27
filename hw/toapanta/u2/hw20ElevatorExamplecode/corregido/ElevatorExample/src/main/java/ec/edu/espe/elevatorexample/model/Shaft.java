
package ec.edu.espe.elevatorexample.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class Shaft {
    private double height;
    private int shaftId;

    public Shaft(int shaftId, double height) {
        this.shaftId = shaftId;
        this.height = height;
        System.out.println("  [Shaft] Shaft " + shaftId + " initialized (Height: " + height + " meters).");
    }

    public double getHeight() {
        return height;
    }

    public int getShaftId() {
        return shaftId;
    }
}
