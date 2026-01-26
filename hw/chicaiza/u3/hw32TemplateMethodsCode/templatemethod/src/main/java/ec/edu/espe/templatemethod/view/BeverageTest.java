package ec.edu.espe.templatemethod.view;

import ec.edu.espe.templatemethod.model.Coffee;
import ec.edu.espe.templatemethod.model.HotChocolate;
import ec.edu.espe.templatemethod.model.Tea;

/**
 *
 * @author Daniel
 */
public class BeverageTest {
    public static void main(String[] args) {
        Tea tea = new Tea();
        Coffee coffee = new Coffee();
        HotChocolate hotChocolate = new HotChocolate();

        System.out.println("\nMaking tea ...");
        tea.prepareRecipe();

        System.out.println("\nMaking coffee ...");
        coffee.prepareRecipe();
        
        System.out.println("\nMaking hot chocolate ...");
        hotChocolate.prepareRecipe();
    }    
}
