package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Joseph Medina Espe
 */
public class Location {
    int xCoordinate;
    int yCoordinate;

    @Override
    public String toString() {
        return "Location{" + "xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + '}';
    }    

    public Location(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
  
}
