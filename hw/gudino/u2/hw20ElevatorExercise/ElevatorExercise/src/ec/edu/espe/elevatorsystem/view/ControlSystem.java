
package ec.edu.espe.elevatorsystem.view;

import ec.edu.espe.elevatorsystem.model.Elevator;
import ec.edu.espe.elevatorsystem.model.StatisticsRecord;

import java.util.ArrayList;
import java.util.List;

public class ControlSystem {
    private List<Elevator> elevators;
    private StatisticsRecord statisticsRecord;

    public ControlSystem() {
        this.elevators = new ArrayList<>();
        this.statisticsRecord = new StatisticsRecord();
    }

    public Elevator selectElevator() {
        if (elevators.isEmpty()) return null;
        // Simple strategy: return first idle elevator (direction == 0)
        for (Elevator e : elevators) {
            if (e != null /* && e.getDirection() == 0 */) {
                return e;
            }
        }
        return elevators.get(0);
    }

    public void updateElevatorState() {
        // placeholder: aquí actualizarías estados, leer sensores, registrar viajes, etc.
        System.out.println("Updating elevator states...");
    }

    public void addElevator(Elevator e) { elevators.add(e); }
    public List<Elevator> getElevators() { return elevators; }
    public StatisticsRecord getStatisticsRecord() { return statisticsRecord; }
}
