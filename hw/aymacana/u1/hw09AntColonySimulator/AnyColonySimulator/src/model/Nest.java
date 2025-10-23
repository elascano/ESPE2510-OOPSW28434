package model;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
import java.util.ArrayList;
import java.util.List;

public class Nest {

    private Cell position;
    private int stockMg;
    private final List<Ant> residentAnts;

    public Nest(Cell position) {
        this.position = position;
        this.stockMg = 0;
        this.residentAnts = new ArrayList<>();
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public int getStockMg() {
        return stockMg;
    }

    public void addFood(Food f) {
        if (f != null) {
            stockMg += f.getAmount();
            if (stockMg < 0) {
                stockMg = 0;
            }
        }
    }

    public boolean consumeFood(int amount) {
        if (stockMg >= amount && amount > 0) {
            stockMg -= amount;
            return true;
        }
        return false;
    }

    public Ant spawnIfPossible(Colony colony) {
        if (stockMg >= 8) {
            if (consumeFood(6)) {
                Ant baby = new Ant(this.position, 1, colony, this);
                residentAnts.add(baby);
                return baby;
            }
        }
        return null;
    }

    public List<Ant> getResidentAnts() {
        return residentAnts;
    }

    @Override
    public String toString() {
        return "Nest at " + position + " stock=" + stockMg + "mg";
    }
}
