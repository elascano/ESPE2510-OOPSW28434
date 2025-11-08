package ec.edu.espe.calculator.view;

import ec.edu.espe.basicoperation.Operation;

/**
 *
 * @author Paulo Ramos
 */
public class BasicCalculator {
    public static void main(String[] args) {
        
        float addend1;
        float addend2;
        float total;
        
        addend1 = 1.3F;
        addend2 = 2.5F;
        
        total = Operation.add(addend1, addend2);
        System.out.println("Total --> " +total);
        
        float minued;
        float substrahend ;
        float difference;
        
        minued = 8.4F;
        substrahend = 4.5F;
        difference = Operation.subtract (minued, substrahend);
        System.out.println("Difference --> " + difference);
        
      
    }
    
}
