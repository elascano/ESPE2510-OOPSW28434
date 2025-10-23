package ec.edu.espe.chickenfarmsimulator.model;

/**
 *
 * @author Maryuri Quina, @ESPE
 */
public class Poop {
    private int amount;

    @Override
    public String toString() {
        return "Poop{" + "amount=" + amount + '}';
    }
    
    public Poop(int amount) {
        this.amount = amount;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }
}
