package ec.edu.espe.hwtemplatemethod.view;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
import ec.edu.espe.hwtemplatemethod.model.Coffee;
import ec.edu.espe.hwtemplatemethod.model.Tea;

public class BeverageTest {

    public static void main(String[] args) {

        Tea tea = new Tea();
        Coffee coffee = new Coffee();

        System.out.println("\nMaking tea...");
        tea.prepareRecipe();

        System.out.println("\nMaking coffee...");
        coffee.prepareRecipe();
    }
}
