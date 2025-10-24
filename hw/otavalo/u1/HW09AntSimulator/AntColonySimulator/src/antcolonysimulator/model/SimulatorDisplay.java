package antcolonysimulator.model;

public class SimulatorDisplay {
    private Area area;

    public SimulatorDisplay(Area area) {
        this.area = area;
    }

    public void showStatus(int tick) {
        System.out.println("Tick " + tick + " executed.");
    }
}
