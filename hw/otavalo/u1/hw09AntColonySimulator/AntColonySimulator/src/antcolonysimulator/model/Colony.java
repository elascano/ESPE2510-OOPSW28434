package antcolonysimulator.model;

import java.util.Random;

public class Colony {
    private Nest nest;
    private Ant[] ants;
    private int antCount;
    private Random random = new Random();
    
    private static final int MAX_ANTS = 50; 
    private static final int INITIAL_ANTS = 2;

    public Colony(Nest nest) {
        this.nest = nest;
        this.ants = new Ant[MAX_ANTS]; 
        this.antCount = 0;
        
        for (int i = 0; i < INITIAL_ANTS; i++) {
            Ant ant = new Ant(nest.getPosition(), nest.getArea(), this); 
            addAnt(ant);
            nest.getPosition().addAnt(ant);
        }
    }

    public void addAnt(Ant ant) {
        if (antCount < ants.length) {
            ants[antCount] = ant;
            antCount++;
        }
    }

    public Ant[] getAnts() { return ants; }
    public int getAntCount() { return antCount; }
    public Nest getNest() { return nest; }

    public void run() {

        if (random.nextDouble() < 0.2 && antCount < ants.length) {
            Ant newAnt = new Ant(nest.getPosition(), nest.getArea(), this); 
            addAnt(newAnt);
            nest.getPosition().addAnt(newAnt);
            System.out.println("Newly born ant in (" + nest.getRow() + ", " + nest.getCol() + ")");
        }

        for (int i = 0; i < antCount; i++) {
            if (ants[i] != null) {
                ants[i].run();
            }
        }
    }
}