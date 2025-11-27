package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
import java.util.ArrayList;
import java.util.List;

public class ControlPanel {

    private String panelId;
    private List<FloorButton> floorButtons;
    private List<ActionButton> actionButtons;
    private Elevator parentElevator;

    public ControlPanel(String panelId, Elevator parentElevator) {
        this.panelId = panelId;
        this.parentElevator = parentElevator;
        this.floorButtons = new ArrayList<>();
        this.actionButtons = new ArrayList<>();
    }

    public void pressFloorButton(int floorNumber) {
        System.out.println("ControlPanel: Floor button " + floorNumber + " pressed");
    }

    public void showCurrentFloor(int floorNumber) {
        System.out.println("ControlPanel: Showing current floor " + floorNumber);
    }

    public List<Button> getPressedButtons() {
        System.out.println("ControlPanel: Getting pressed buttons");
        return new ArrayList<>();
    }

    public String getPanelId() {
        return panelId;
    }

    public Elevator getParentElevator() {
        return parentElevator;
    }
}
