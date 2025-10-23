
package entities;

/**
 *
 * @author ADRIAN TOAPANTA
 */

public class Food {
    private double amount; // en mg
    
    public Food(double amount) {
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void decrease(double value) {
        this.amount = Math.max(0, this.amount - value);
    }
    
    public boolean isEmpty() {
        return amount <= 0;
    }
}