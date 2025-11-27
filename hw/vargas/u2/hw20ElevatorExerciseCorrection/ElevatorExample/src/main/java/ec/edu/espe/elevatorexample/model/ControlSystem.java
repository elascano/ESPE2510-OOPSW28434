
package ec.edu.espe.elevatorexample.model;


        

/**
 *
 * @author Cesar Vargas, Paradigm, @ESPE
 */
public class ControlSystem {
    private Elevator elevator;

    public ControlSystem(Elevator elevator) {
        this.elevator = elevator;
    }

    public void requestElevator(int targetFloor) {
        int currentFloor = elevator.getCurrentFloor();
        System.out.println("\n Request received: Move to floor " + targetFloor + " from " + currentFloor + ".");

        if (targetFloor == currentFloor) {
            System.out.println(" Already at target floor " + targetFloor + ". Opening doors.");
            elevator.openDoor();
            return;
        }

        elevator.closeDoor();

        if (targetFloor > currentFloor) {
            moveElevator(targetFloor, 1); 
        } else {
            moveElevator(targetFloor, -1); 
        }

        elevator.getMotor().applyBrakes(targetFloor);
        elevator.setCurrentFloor(targetFloor);
        System.out.println("\n Elevator arrived at floor " + targetFloor);
        System.out.println("I'm on floor " + targetFloor );
        elevator.openDoor();
    }

    private void moveElevator(int targetFloor, int direction) {
        int current = elevator.getCurrentFloor();

        while (current != targetFloor) {
            if (direction > 0) {
                current++;
                elevator.getMotor().moveUp(current - 1);
            } else {
                current--;
                elevator.getMotor().moveDown(current + 1);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}