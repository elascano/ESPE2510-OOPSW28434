package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Motor {

    private int cables;
    private double currentSpeed;
    private double maxSpeed;

    public Motor() {
        this.cables = 6;
        this.currentSpeed = 0.0;
        this.maxSpeed = 2.5;
    }

    public void accelerate() {
        System.out.println("Motor: Accelerating");
    }

    public void decelerate() {
        System.out.println("Motor: Decelerating");
    }

    public int getCables() {
        return cables;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}
