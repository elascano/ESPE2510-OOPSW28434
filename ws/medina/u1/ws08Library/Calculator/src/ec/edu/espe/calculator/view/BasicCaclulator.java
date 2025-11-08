package ec.edu.espe.calculator.view;

import ec.edu.espe.basicoperations.Operation;

/**
 *
 * @author Joseph B. Medina
 */
public class BasicCaclulator {
    public static void main(String[] args){
        float difference ;
        float add ;
        add = Operation.add(1.2F, 2.3F);
        System.out.println("======ADDITION======");
        System.out.println("| The add is -->  " + add + " |" );
        System.out.println("");
        System.out.println("======SUBSTRACTION======");
        difference = Operation.substract(2.3F, 4.5f);
        System.out.println("| The diffrence is --> " + difference + " |");
        System.out.println("");
        
        
    }
    
    
}
