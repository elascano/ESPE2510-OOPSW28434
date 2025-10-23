package model;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
import java.util.Random;

public class Ant {

    private static final Random R = new Random();
    private Cell position;
    private int weight; // mg
    private int carrying; // mg currently carrying
    private Colony colony;
    private Nest home;
    private int ticksSinceLastWeightDecay = 0;
    private int carryingCapacity = 5;
    private int lastDirRow = 0;
    private int lastDirCol = 1;

    public Ant(Cell position, int initialWeight, Colony colony, Nest home) {
        this.position = position;
        this.weight = initialWeight;
        this.colony = colony;
        this.home = home;
    }

    public void tick(Area area) {
        ticksSinceLastWeightDecay++;

        if (ticksSinceLastWeightDecay >= 25) {
            if (weight > 0) {
                weight -= 1;
            }
            ticksSinceLastWeightDecay = 0;
        }

        if (position.equals(home.getPosition())) {
            if (carrying > 0) {
                home.addFood(new Food(carrying));
                carrying = 0;
            }

            if (weight < 5 && home.getStockMg() >= 1) {
                if (home.consumeFood(1)) {
                    weight = Math.min(5, weight + 1);
                }
            }

            if (carrying == 0 && weight >= 1) {
                Cell exit = area.getRandomNeighbor(position, 0, 0);
                if (exit != null) {
                    moveTo(exit);
                }
            }
            return;
        }

        FoodPile fp = position.getFoodPile();
        if (fp != null && fp.getPileAmount() > 0 && carrying < carryingCapacity) {
            int canTake = Math.min(carryingCapacity - carrying, fp.getPileAmount());
            Food f = fp.yieldFood(canTake);
            carrying += f.getAmount();
            if (fp.getPileAmount() <= 0) {
                position.setFoodPile(null);
            }
            System.out.println("The ant runs " + f.getAmount() + "mg! carrying now: " + carrying + "mg");
        }

        if (carrying > 0) {
            position.receivePheromone(new PheromoneDrop(100));
            Cell next = area.getNeighborTowards(position, home.getPosition());
            if (next != null) {
                moveTo(next);
            } else {
                moveRandom(area);
            }
        } else {
            Cell foodNeighbor = findFoodInNeighbors(area);
            if (foodNeighbor != null) {
                moveTo(foodNeighbor);
                return;
            }

            PheromoneDrop p = position.getPheromone();
            if (p != null && p.getLevel() > 0) {
                Cell best = area.findNeighborWithLowestNonZeroPheromone(position);
                if (best != null) {
                    moveTo(best);
                } else {
                    moveRandom(area);
                }
            } else {
                Cell neighborWithP = area.findNeighborWithAnyPheromone(position);
                if (neighborWithP != null) {
                    moveTo(neighborWithP);
                } else {
                    moveRandom(area);
                }
            }
        }
    }

    private Cell findFoodInNeighbors(Area area) {
        int r = position.getRow(), c = position.getCol();
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) {
                    continue;
                }
                Cell neighbor = area.getCell(r + dr, c + dc);
                if (neighbor != null && neighbor.getFoodPile() != null
                        && neighbor.getFoodPile().getPileAmount() > 0) {
                    return neighbor;
                }
            }
        }
        return null;
    }

    private void moveRandom(Area area) {
        Cell randomNeighbor = area.getRandomNeighbor(position, lastDirRow, lastDirCol);
        if (randomNeighbor != null) {
            moveTo(randomNeighbor);
        }
    }

    public void moveTo(Cell next) {
        if (next == null) {
            return;
        }
        lastDirRow = next.getRow() - position.getRow();
        lastDirCol = next.getCol() - position.getCol();
        position.removeAnt(this);
        position = next;
        position.addAnt(this);
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCarrying() {
        return carrying;
    }

    public void setCarrying(int carrying) {
        this.carrying = carrying;
    }

    public Colony getColony() {
        return colony;
    }

    public Nest getHome() {
        return home;
    }

    @Override
    public String toString() {
        return "Ant{pos=" + position + ", weight=" + weight + "mg, carrying=" + carrying + "mg}";
    }
}
