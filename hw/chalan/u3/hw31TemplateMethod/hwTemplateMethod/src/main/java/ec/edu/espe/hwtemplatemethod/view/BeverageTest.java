package ec.edu.espe.hwtemplatemethod.view;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
import ec.edu.espe.hwtemplatemethod.model.Coffee;
import ec.edu.espe.hwtemplatemethod.model.Tea;

public class BeverageTest {

    public static void main(String[] args) {

        Tea tea = new Tea(); // Concrete class
        Coffee coffee = new Coffee(); // Concrete class

        System.out.println("\nMaking tea...");
        tea.prepareRecipe(); // Template call

        System.out.println("\nMaking coffee...");
        coffee.prepareRecipe(); // Template call
    }
}
