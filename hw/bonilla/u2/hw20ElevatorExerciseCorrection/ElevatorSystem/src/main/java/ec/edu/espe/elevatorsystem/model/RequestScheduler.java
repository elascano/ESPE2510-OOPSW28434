package ec.edu.espe.elevatorsystem.model;

import java.util.List;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public interface RequestScheduler {

    Elevator selectElevator(ElevatorRequest request, List<Elevator> elevators);

    void prioritizeRequest(List<ElevatorRequest> pendingRequests);

    boolean canAcceptRequest(Elevator elevator, ElevatorRequest request);
}
