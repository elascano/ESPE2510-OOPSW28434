package model;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
import java.util.ArrayList;
import java.util.List;

public class Colony {

    private Nest nest;
    private final List<Ant> ants;

    public Colony(Nest nest) {
        this.nest = nest;
        this.ants = new ArrayList<>();
    }

    public void addAnt(Ant a) {
        if (a != null) {
            ants.add(a);
        }
    }

    public Nest getNest() {
        return nest;
    }

    public void setNest(Nest nest) {
        this.nest = nest;
    }

    public List<Ant> getAnts() {
        return ants;
    }

    public void tickSpawn() {
        if (ants.size() < 15 && nest.getStockMg() >= 8) {
            Ant newAnt = nest.spawnIfPossible(this);
            if (newAnt != null) {
                ants.add(newAnt);
                nest.getPosition().addAnt(newAnt);
                System.out.println(" New ant created! Population: " + ants.size());
            }
        }
    }

    @Override
    public String toString() {
        return "Colony with nest: " + nest + ", population: " + ants.size() + " ants";
    }
}
