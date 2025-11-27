package ec.edu.espe.elevatorsystem.controller;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */
import ec.edu.espe.elevatorsystem.model.Elevator;

public class ControlSystem {

    private Elevator elevator = new Elevator();

    public void assignElevator(int floor) {
        System.out.println("Asignando ascensor...");
        elevator.addRequest(floor);
        elevator.moveUp();
        elevator.goTo(floor);
        elevator.openDoors();
    }

    public void updateElevator() {
        System.out.println("Actualizando estado del ascensor...");
    }

    public void recordStatistics() {
        System.out.println("Registrando estadísticas...");
    }
}

