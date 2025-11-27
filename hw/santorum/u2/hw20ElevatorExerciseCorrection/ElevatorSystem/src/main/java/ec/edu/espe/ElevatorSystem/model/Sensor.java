package ec.edu.espe.ElevatorSystem.model;

/**
 * @author JOSUE ROJAS y THAIS SANTORUM
 */

public class Sensor {
    private int xCoordinate;
    private int yCoordinate;
    private float speed;

    public Sensor(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.speed = 0.0f;
    }

    public void showDirection(int targetX, int targetY) {
        String direction;
        
        if (targetY > this.yCoordinate) {
            direction = "UP";
        } else if (targetY < this.yCoordinate) {
            direction = "DOWN";
        } else {
            direction = "STATIONARY";
        }
        
        System.out.println("[SENSOR] Coordinates (" + this.xCoordinate + "," + this.yCoordinate + ") -> Moving " + direction + " to floor " + targetY);
        
        this.yCoordinate = targetY;
        this.xCoordinate = targetX;
    }

    public void showSpeed(float speed) {
        this.speed = speed;
        System.out.println("[SENSOR] Current Speed: " + speed + " m/s");
    }
}   