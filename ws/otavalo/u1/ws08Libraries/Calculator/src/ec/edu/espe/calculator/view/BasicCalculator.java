package ec.edu.espe.calculator.view;
import ec.edu.espe.basicoperations.Operation;
/**
 *
 * @author Arelys Otavalo
 */
public class BasicCalculator {
    public static void main(String[] args) {
                float addend1;
                float addend2;
                float total;
                
                addend1 = 1.3F;
                addend2 = 2.5F;
                total = Operation.add(addend1, addend2);
                
            System.out.println("Total --> " + total);
            
            
            float minuend;
            float substrahend;
            float difference;
            
            minuend = 8.4F;
            substrahend = 4.5F;
            difference = Operation.subtract(minuend, substrahend);
               System.out.println("Difference --> " + difference);
               
    }
}
