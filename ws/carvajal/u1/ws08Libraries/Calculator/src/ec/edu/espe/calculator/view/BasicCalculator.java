package ec.edu.espe.calculator.view;

import ec.edu.espe.basicoperations.Operation;



/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */
public class BasicCalculator {
    public static void main(String[] args) {
        
        float addend1;
        float addend2;
        float total;
        
        addend1 = 1.3F;
        addend2 = 1.3F;
        
        total = Operation.add(addend1, addend2);
        
       
        System.out.println("total -->"+ total+ "\n");
        
             
        float substrahend;
        float minuend;
        float result;
        
        substrahend = 4.6F;
        minuend = 6.7F;
        
        result = Operation.substract(minuend, substrahend);
        
        
        System.out.println("result -->"+ result);
            
    }
    
}
