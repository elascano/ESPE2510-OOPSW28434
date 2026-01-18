package ec.edu.espe.singletown.view;

import ec.edu.espe.singletown.controller.TaxController;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class Calculator {

    public static void main(String args[]) {

        TaxController controller = new TaxController();

        controller.setTaxPercentage(12f);

        System.out.println("Tax: " + controller.getTaxInfo());
        System.out.println("Total: " + controller.getTotal(100));
    }
}