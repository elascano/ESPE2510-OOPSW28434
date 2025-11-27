package ec.edu.espe.elevatorsystem.controller;
import ec.edu.espe.elevatorsystem.model.Elevator;
import ec.edu.espe.elevatorsystem.model.Button;
import java.util.ArrayList;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class ElevatorControlSystem {
    private ArrayList<Elevator> elevators;

    public void requestElevator(int floor) {
        System.out.println("System received request for floor: " + floor);
    }

    public void configureElevatorRange(int elevatorId, ArrayList<Integer> allowedFloors) {
        System.out.println("Configuring floors for elevator " + elevatorId);
    }

    public Elevator findBestElevator(int floor) {
        System.out.println("Algorithm finding best elevator for floor " + floor);
        return new Elevator(); 
    }

    public void detectedCurrentRequest(Button button) {
        System.out.println("Detected request from button: " + button.getName());
    }
    
    public void saveRequest() {
        System.out.println("Request saved to history.");
    }
    
    public void saveTime() {
        System.out.println("Transaction time saved.");
    }
}
