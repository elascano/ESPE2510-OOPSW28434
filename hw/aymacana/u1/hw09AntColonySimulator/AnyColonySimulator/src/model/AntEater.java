package model;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
import java.util.Random;

public class AntEater {

    private Cell position;
    private int antEatenCounter;
    private int actionCounter;
    private State state;
    private static final Random R = new Random();

    public enum State {
        HUNGRY,
        EATING,
        SLEEPING
    }

    public AntEater(Cell position) {
        this.position = position;
        this.antEatenCounter = 0;
        this.actionCounter = 0;
        this.state = State.HUNGRY;
    }

    public void tick(Area area) {
        switch (state) {
            case HUNGRY:
                if (!position.getAnts().isEmpty()) {
                    Ant edibleAnt = findEdibleAnt();
                    if (edibleAnt != null) {
                        state = State.EATING;
                        actionCounter = 5;
                    } else {
                        Cell next = area.getRandomNeighbor(position, 0, 0);
                        if (next != null) {
                            position = next;
                        }
                    }
                } else {
                    Cell next = area.getRandomNeighbor(position, 0, 0);
                    if (next != null) {
                        position = next;
                    }
                }
                break;

            case EATING:
                actionCounter--;
                if (actionCounter <= 0) {
                    Ant victim = findEdibleAnt();
                    if (victim != null) {
                        position.removeAnt(victim);
                        antEatenCounter++;
                        if (victim.getColony() != null) {
                            victim.getColony().getAnts().remove(victim);
                        }

                        if (antEatenCounter >= 15) {
                            state = State.SLEEPING;
                            actionCounter = 60;
                        } else {
                            if (findEdibleAnt() != null) {
                                actionCounter = 3;
                            } else {
                                state = State.HUNGRY;
                            }
                        }
                    } else {
                        state = State.HUNGRY;
                    }
                }
                break;

            case SLEEPING:
                actionCounter--;
                System.out.println("AntEater: SLEEPING - Ticks remaining: " + actionCounter);
                if (actionCounter <= 0) {
                    antEatenCounter = 0;
                    state = State.HUNGRY;
                    System.out.println("AntEater: WOJE UP changing to HUNGRY");
                }
                break;
        }
    }

    private Ant findEdibleAnt() {
        for (Ant ant : position.getAnts()) {
            if (!isAntInNest(ant)) {
                return ant;
            }
        }
        return null;
    }

    private boolean isAntInNest(Ant ant) {
        if (ant == null || ant.getHome() == null) {
            return false;
        }
        return ant.getPosition().equals(ant.getHome().getPosition());
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public int getAntEatenCounter() {
        return antEatenCounter;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "AntEater{" + position + ", state=" + state + ", eaten=" + antEatenCounter + "}";
    }
}
