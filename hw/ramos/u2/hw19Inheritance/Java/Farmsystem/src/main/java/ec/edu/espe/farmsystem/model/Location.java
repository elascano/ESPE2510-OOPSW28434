package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Paulo Ramos
 */
public class Location {
    private int xCoordinate;
    private int yCoordinate;

    @Override
    public String toString() {
        return "Location{" + "xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + '}';
    }

    
    
    public Location(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
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
