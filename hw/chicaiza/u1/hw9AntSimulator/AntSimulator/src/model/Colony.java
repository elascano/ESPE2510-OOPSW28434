package model;

import java.util.List;

public class Colony {
    private Nest nest;
    private List<Ant> ants;
    
    public Colony(Nest nest) {
        this.nest = nest;
        // Initialize the ant list if needed
    }

    // Get the colony's nest
    public Nest getNest() {
        return nest;
    }

    // Add an ant to the colony
    public void addAnt(Ant ant) {}

    // Update all ants in this colony for a tick
    public void runTick() {}
}