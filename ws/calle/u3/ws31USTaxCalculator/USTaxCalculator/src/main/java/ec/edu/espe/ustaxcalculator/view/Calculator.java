package ec.edu.espe.ustaxcalculator.view;
import ec.edu.espe.ustaxcalculator.controller.TaxController;
import ec.edu.espe.ustaxcalculator.model.USTax;

/**
 *
 * @author Emily Calle, @ESPE
 */
public class Calculator {
    private static TaxController controller;

    public static void main(String[] args) {
        USTax usTax = USTax.getInstance();
        Calculator calculator = new Calculator();
        
        controller = new TaxController(usTax, calculator);
        
        System.out.println(usTax.getSingletonData());
        usTax.setTaxPercentage(0.12f);
        
        controller.calculateSalesTotal(100.00);
        
        System.out.println("New state: " + usTax.getSingletonData());
    }

    public void display() {
        System.out.println("TOTAL WITH IVA: " + controller.getLastTotal());
    }
}