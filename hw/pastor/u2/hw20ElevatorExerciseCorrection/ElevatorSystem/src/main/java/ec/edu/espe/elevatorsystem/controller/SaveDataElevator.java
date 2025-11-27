package ec.edu.espe.elevatorsystem.controller;
import ec.edu.espe.elevatorsystem.model.Person;
import java.util.ArrayList;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class SaveDataElevator {
    public double calculateMostPopularFloor() {
        System.out.println("Calculating statistics: Most Popular Floor.");
        return 0.0;
    }

    public void recordTrip(ArrayList<Person> people, String time) {
        System.out.println("Recording trip data to database/file.");
    }

    public double calculateAverageWeight() {
        System.out.println("Calculating average load.");
        return 0.0;
    }

    public void recordExit(int numberFloor) {
        System.out.println("Recording passenger exit at floor " + numberFloor);
    }

    public void recordEntry(int numberFloor) {
        System.out.println("Recording passenger entry at floor " + numberFloor);
    }
}
