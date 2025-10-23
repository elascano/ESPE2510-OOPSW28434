package ec.edu.espe.chickenfarmsimulator.model;

/**
 *
 * @author LABS-ESPE
 */
public class Poop {

    private int amount;

    public String toString() {
        return "Poop{" + "amount = " + amount + "}";
    }

    public Poop(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

}
