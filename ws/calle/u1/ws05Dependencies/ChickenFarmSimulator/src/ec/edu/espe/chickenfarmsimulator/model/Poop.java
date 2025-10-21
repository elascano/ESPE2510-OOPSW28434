package ec.edu.espe.chickenfarmsimulator.model;

/**
 *
 * @author Emily Calle
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
      * @return the mount
      */
      

    public int getAmount() {
        return amount;
    }
   
}
