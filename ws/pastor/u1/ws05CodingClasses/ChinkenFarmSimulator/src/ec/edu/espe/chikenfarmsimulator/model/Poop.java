package ec.edu.espe.chikenfarmsimulator.model; 
/**
 *
 * @author Mathews Pastor
 */
public class Poop {
    private int amount;

    public Poop(int amount) {
        this.amount = amount;
    }




    @Override
    public String toString() {
        return "The amount of poop was " + getAmount();
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
