package ec.espe.edu.chickenFarmSimulator.model;
import java.util.Random;
/**
 *
 * @author Mathews Pastor
 */
public class Poop{
    private int amount;
    
    public Poop(){
        Random random = new Random();
        this.amount = random.nextInt(51);
    }

    @Override
    public String toString() {
        return "Poop amount => " + amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
