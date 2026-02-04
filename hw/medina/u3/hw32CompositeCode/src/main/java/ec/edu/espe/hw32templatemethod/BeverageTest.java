package ec.edu.espe.hw32templatemethod;

import ec.edu.espe.hw32templatemethod.model.Coffee;
import ec.edu.espe.hw32templatemethod.model.Tea;

/**
 *
 * @author Joseph B. Medina
 */
public class BeverageTest {

    public static void main(String[] args) {
        
        Tea tea = new Tea();
        Coffee coffee = new Coffee();

        System.out.println("\nMaking tea...");
        tea.prepareRecipe();

        System.out.println("\nMaking coffe...");
        coffee.prepareRecipe();

    }
}
