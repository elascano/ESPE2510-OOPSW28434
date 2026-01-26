package ec.edu.espe.templatemethod.view;

import ec.edu.espe.templatemethod.model.Coffe;
import ec.edu.espe.templatemethod.model.Tea;

/**
 *
 * @author Paulo Ramos
 */
public class BeverageTest {
    public static void main(String[] args) {
        Tea tea = new Tea();
        Coffe coffe = new Coffe();
        
        System.out.println("\n Making tea...");
        tea.prepareRecipe();
        
        System.out.println("\n Making coffe...");
        coffe.prepareRecipe();
    }
    
}
