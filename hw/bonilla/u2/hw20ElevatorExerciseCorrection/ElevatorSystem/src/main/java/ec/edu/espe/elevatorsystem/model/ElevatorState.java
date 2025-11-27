package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public interface ElevatorState {

    void move(int targetFloor);

    void stop();

    void openDoors();

    void closeDoors();
}
