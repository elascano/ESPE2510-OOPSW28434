package model;

import java.util.List;

public class Colony {
    private Nest nest;
    private List<Ant> ants;
    
    public Nest getNest() {
    return nest;
}

    public Colony(Nest nest) {
        this.nest = nest;
    }

    public void addAnt(Ant ant) {}
    public void runTick() {}
}
