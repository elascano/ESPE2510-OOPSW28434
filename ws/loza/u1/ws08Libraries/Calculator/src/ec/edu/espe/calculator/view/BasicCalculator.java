package ec.edu.espe.calculator.view;

import ec.edu.espe.basicoperations.Operation;

/**
 *
 * @author Steven Loza
 */
public class BasicCalculator {

    public static void main(String[] args) {

        float addend1;
        float addend2;
        float total;
        addend1 = 1.4F;
        addend2 = 2.7F;

        total = Operation.add(addend1, addend2);
        System.out.println("total ---" + total);

        float minuend;
        float subtrahend;
        float totalsubstrac;
        float difference;

        minuend = 3;
        subtrahend = 6;
        difference = minuend - subtrahend;
        totalsubstrac = Operation.substract(minuend, subtrahend);
         System.out.println("substrac total ---" + totalsubstrac);
    }
}
