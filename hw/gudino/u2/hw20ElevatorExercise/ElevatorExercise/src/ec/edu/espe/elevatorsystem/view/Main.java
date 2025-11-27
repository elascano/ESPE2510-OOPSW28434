package ec.edu.espe.elevatorsystem.view;

import ec.edu.espe.elevatorsystem.model.Elevator;

public class Main {

    public static void main(String[] args) {

        System.out.println("Starting Elevator System...");

        ControlSystem controlSystem = new ControlSystem();

        Elevator elevator1 = new Elevator(1);
        Elevator elevator2 = new Elevator(2);

        controlSystem.addElevator(elevator1);
        controlSystem.addElevator(elevator2);

        Elevator selected = controlSystem.selectElevator();
        System.out.println("Elevator selected: " + selected.getId());

        selected.moveToFloor(4);
        selected.openDoors();
        selected.closeDoors();
        
        controlSystem.updateElevatorState();

        System.out.println("Program finished.");
    }
}
