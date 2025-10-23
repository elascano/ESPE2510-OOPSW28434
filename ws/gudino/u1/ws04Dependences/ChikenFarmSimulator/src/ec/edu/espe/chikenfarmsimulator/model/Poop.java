package ec.edu.espe.chikenfarmsimulator.model;

/**
 *
 * @author Bryan Gudino, KNOWLEDGE ENCAPSULATE, @ESPE
 */
public class Poop {
    private int amount;

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Poop(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Poop{" + "amount=" + amount + '}';
    }
}
