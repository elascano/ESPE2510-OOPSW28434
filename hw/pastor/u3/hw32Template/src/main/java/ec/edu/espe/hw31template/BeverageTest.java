package ec.edu.espe.hw31template;

import ec.edu.espe.hw32template.model.Coffee;
import ec.edu.espe.hw32template.model.Tea;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
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
