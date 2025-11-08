package ec.espe.edu.calculator.view;

import ec.espe.edu.basioperations.Operation;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class BasicCalculator {
    public static void main(String[] args) {
        float addend1;
        float addend2;
        float total;
        
        addend1 = 1.28F;
        addend2 = 3.45F;
        total = Operation.add(5.44F,1.34F);
        System.out.println("Total --> "+ total);
        
        float minuend;
        float substrahend;
        float difference;
        
        minuend = 8.4F;
        substrahend = 4.5F;
        difference = Operation.substract(minuend, substrahend);
        System.out.println("Difference --> " + difference);
        
        
    }
    
}
