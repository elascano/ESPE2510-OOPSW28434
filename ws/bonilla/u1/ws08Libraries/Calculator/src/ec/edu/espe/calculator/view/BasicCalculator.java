package ec.edu.espe.calculator.view;

import ec.edu.espe.basicoperations.Operation;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class BasicCalculator {
    public static void main(String[] args) {
        float addend1;
        float addend2;
        float total;

        addend1 = 1.3F;
        addend2 = 2.5F;

        total = Operation.add(addend1, addend2);
        System.out.println("total = " + total);

        float minuend;
        float subtrahend;
        float difference;

        minuend = 9.5F;
        subtrahend = 8F;

        difference = Operation.subtract(minuend, subtrahend);
        System.out.println("result = " + difference);
    }
    
}
