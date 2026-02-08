package ec.edu.espe.hwtemplatemethod.view;

import ec.edu.espe.hwtemplatemethod.model.Coffee;
import ec.edu.espe.hwtemplatemethod.model.Tea;
import ec.edu.espe.hwtemplatemethod.model.HutChocolate;

public class BeverageTest {

    public static void main(String[] args) {

        Tea tea = new Tea();
        Coffee coffee = new Coffee();
        HutChocolate hotChocolate = new HutChocolate();

        System.out.println("\nMaking tea...");
        tea.prepareRecipe();

        System.out.println("\nMaking coffee...");
        coffee.prepareRecipe();

        System.out.println("\nMaking hot chocolate...");
        hotChocolate.prepareRecipe();
    }
}
