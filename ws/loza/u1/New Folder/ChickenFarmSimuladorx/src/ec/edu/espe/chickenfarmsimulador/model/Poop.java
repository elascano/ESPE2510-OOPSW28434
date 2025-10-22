package ec.edu.espe.chickenfarmsimulador.model;

/**
 *
 * @author Joseph Medina
 */
public class Poop {
    private int amount;
   
    @Override
    public String toString() {
        return "Poop {" + "amount = " + amount + '}';
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

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
   
   
}    