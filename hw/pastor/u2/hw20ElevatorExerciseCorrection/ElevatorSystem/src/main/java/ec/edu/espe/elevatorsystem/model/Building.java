package ec.edu.espe.elevatorsystem.model;
import java.util.ArrayList;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Building {
    private String name;
    private ArrayList<Floor> floors;

    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }
}
