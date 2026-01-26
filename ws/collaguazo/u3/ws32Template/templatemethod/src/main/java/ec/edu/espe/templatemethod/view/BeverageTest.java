package ec.edu.espe.templatemethod.view;

import ec.edu.espe.templatemethod.model.Coffee;
import ec.edu.espe.templatemethod.model.Hotchocolate;
import ec.edu.espe.templatemethod.model.Tea;

/**
 *
 * @author Pablo Collaguazo
 */
public class BeverageTest {
    public static void main(String[] args) {
        Tea tea = new Tea();
        Coffee coffee = new Coffee();
        Hotchocolate hotchocolate = new Hotchocolate();

        System.out.println("\nMaking tea ...");
        tea.prepareRecipe();

        System.out.println("\nMaking coffee ...");
        coffee.prepareRecipe();
        System.out.println("\nMaking chocolate ...");
        hotchocolate.prepareRecipe();
    }    
}
