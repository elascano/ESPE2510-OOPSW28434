package ec.edu.espe.calculatorustax.view;

import ec.edu.espe.calculatorustax.model.USTax;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Calculator {

    public static void main(String[] args) {
        USTax tax = USTax.getInstance();

        tax.getSingletonData();

        float amount = 100.0f;
        float total = tax.salesTotal(amount);

        System.out.printf("Total Amount: %.2f", total);
        
    }
}
