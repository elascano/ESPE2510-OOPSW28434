package ec.edu.espe.elevatorsystem.model;

public class Cabin {
    private final ControlPanel panel;

    public Cabin() {
        this.panel = new ControlPanel();
    }

    public ControlPanel getPanel() {
        return panel;
    }
}
