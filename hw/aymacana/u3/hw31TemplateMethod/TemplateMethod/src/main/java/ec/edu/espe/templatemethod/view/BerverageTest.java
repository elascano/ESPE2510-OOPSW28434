package ec.edu.espe.templatemethod.view;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
import ec.edu.espe.templatemethod.model.*;

public class BerverageTest {

    public static void main(String[] args) {

        Tea tea = new Tea();
        Coffe coffee = new Coffe();

        System.out.println(" Making tea..");
        tea.prepareRecipe();

        System.out.println(" Making coffe...");
        coffee.prepareRecipe();

    }
}
