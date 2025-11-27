package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
import java.util.ArrayList;
import java.util.List;

public class Cage {

    private ControlPanel controlPanel;
    private List<Person> currentPassengers;

    public Cage(String panelId, Elevator parentElevator) {
        this.controlPanel = new ControlPanel(panelId, parentElevator);
        this.currentPassengers = new ArrayList<>();
    }

    public void ringAlarm() {
        System.out.println("Cage: Ringing alarm");
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public List<Person> getCurrentPassengers() {
        return currentPassengers;
    }

    public void addPassenger(Person person) {
        System.out.println("Cage: Adding passenger " + person.getId());
        currentPassengers.add(person);
    }

    public void removePassenger(Person person) {
        System.out.println("Cage: Removing passenger " + person.getId());
        currentPassengers.remove(person);
    }
}
