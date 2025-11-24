package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Mathews Pastor, Poower Rangers of Programing, @ESPE
 */
public class Location {
    private int xCoordinate;
    private int yCoordinate;

    @Override
    public String toString() {
        return "Location{" + "xCordinate=" + xCoordinate + ", yCordinate=" + yCoordinate + '}';
    }
    
    public Location(int xCordinate, int yCordinate) {
        this.xCoordinate = xCordinate;
        this.yCoordinate = yCordinate;
    }
    
    /**
     * @return the xCoordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * @param xCoordinate the xCoordinate to set
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * @return the yCoordinate
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * @param yCoordinate the yCoordinate to set
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    
    
}
