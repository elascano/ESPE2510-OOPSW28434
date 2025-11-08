package ec.edu.espe.calculator.view;
import ec.edu.espe.basicoperations.Operation;

/**
 *
 * @author Mathews Pastor
 */
public class BasicCalculator {
    public static void main(String[] args) {
        float addend1;
        float addend2;
        float total;
        
        addend1 = 1.3F;
        addend2 = 2.5F;
        
        total = Operation.add(addend1, addend2);
        System.out.println("total --> " + total);
        
        float subtrahend;
        float minuend;
        float difference;
        
        subtrahend = 2.1F;
        minuend = 3.1F;
        
        difference = Operation.subtract(minuend, subtrahend);
        System.out.println("difference --> " + difference);
       
    }
}
