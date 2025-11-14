package ec.edu.ec.chickenfarmsimulator.model;

/**
 *
 * @author Pablo Collaguazo
 */
public class Poop {
    private int amount;

    @Override
    public String toString() {
        return "Poop{" + "amount=" + amount + '}';
    }

    public Poop(int amount){
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
}
