package ec.edu.espe.singleton;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Calculator {
    public static void main(String[] args) {
        USTax tax = USTax.getInstance();
        System.out.println("The current instance " + tax);
        float price = 1;
        float newPrice = tax.saleTotal(price);
        System.out.println("The first price " + price + " The new price " + newPrice);
        USTax tax2 = USTax.getInstance();
        System.out.println("The current intance should be the same " + tax2);
    }   
}
