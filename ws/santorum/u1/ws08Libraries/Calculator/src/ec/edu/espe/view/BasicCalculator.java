
package ec.edu.espe.view;

import ec.edu.espe.basicoperations.Operation;

/**
 *
 * @author Thais Santórum - Team 6: Paradigm.
 */
public class BasicCalculator {
    
    public static void main(String[] args){
        
        float addend1;
        float addend2;
        float total;
        
        addend1 = 1.3F;
        addend2 = 2.5F;
        
        total = Operation.add(addend1, addend2);
        System.out.println("Total = " + total);
        
    }
}
