package ec.edu.espe.calculator.view;

import ec.edu.espe.basicoperations.Operation;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public class BasicCalculator {

    public static void main(String[] args) {
        //
        float addend1;
        float addend2;
        float total;
        addend1 = 1.2F;
        addend2 = 2.3F;

        total = Operation.add(addend1, addend2);
        System.out.println("total ---" + total);

        float minuend;
        float subtrahend;
        float totalSubstrac;

        minuend = 8.4F;
        subtrahend = 2.5F;
        totalSubstrac = Operation.substract(minuend, subtrahend);
         System.out.println("total diference ---" + totalSubstrac);
    }
}
