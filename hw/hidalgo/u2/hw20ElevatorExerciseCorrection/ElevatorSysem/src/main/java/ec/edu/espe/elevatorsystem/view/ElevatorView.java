package ec.edu.espe.elevatorsystem.view;

import ec.edu.espe.elevatorsystem.model.Direction;

public class ElevatorView {

    public void showFloorRequest(int floor, Direction direction) {
        System.out.println("[Vista] Solicitud desde piso " + floor + " hacia " + direction);
    }

    public void showElevatorArrived(int elevatorId, int floor) {
        System.out.println("[Vista] El ascensor " + elevatorId + " llegó al piso " + floor);
    }
}
