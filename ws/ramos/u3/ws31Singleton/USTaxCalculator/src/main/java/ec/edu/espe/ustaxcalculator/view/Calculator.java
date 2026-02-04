package ec.edu.espe.ustaxcalculator.view;

import ec.edu.espe.ustaxcalculator.controller.TaxController;
import ec.edu.espe.ustaxcalculator.model.USTax;

/**
 *
 * @author Paulo Ramos
 */
public class Calculator {

    public static void main(String args[]) {
        USTax tax = USTax.getInstance();
        float amount = 120f;
        
        tax.setTaxPercentage(12f);
        System.out.println("Tax: " + tax.getSingletonData());
        
        Calculator calculator = new Calculator();
        TaxController taxController = new TaxController(tax, calculator);
        taxController.CalculateSalesTotal(amount);
    }
    
    public void displaySalesTotal(float amount, float total){
        System.out.println("Amount: " + amount );
        System.out.println("Total Sales: " + total );
        
    }
}
