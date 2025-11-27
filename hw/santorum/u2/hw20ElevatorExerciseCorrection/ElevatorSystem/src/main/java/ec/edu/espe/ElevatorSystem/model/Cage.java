package ec.edu.espe.ElevatorSystem.model;

/**
 * @author JOSUE ROJAS y THAIS SANTORUM
 */

public class Cage {
    private ControlPanel controlPanel;

    public Cage() {
        this.controlPanel = new ControlPanel();
    }
    
    public ControlPanel getControlPanel() { return controlPanel; }
}