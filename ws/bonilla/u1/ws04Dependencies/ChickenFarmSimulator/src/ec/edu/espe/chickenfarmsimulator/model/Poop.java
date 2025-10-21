package ec.edu.espe.chickenfarmsimulator.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class Poop {
    private int amount;

    public Poop(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Poop{" + "amount=" + amount + '}';
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
      
}
