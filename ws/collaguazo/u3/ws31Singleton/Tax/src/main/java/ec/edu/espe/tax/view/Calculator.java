package ec.edu.espe.tax.view;

import ec.edu.espe.tax.controller.TaxController;
import ec.edu.espe.tax.model.USTax;

/**
 *
 * @author Pablo Collaguazo
 */
public class Calculator {
    public static void main(String[] args) {
        USTax tax = USTax.getInstance();
        tax.setTaxPercentage(0.15f);
        tax.getSingletonData();
        float amount = 120.0f;

        System.out.println("The Tax of this system is $" + tax.getTaxPercentage() );
     
        Calculator calculator = new Calculator();
        TaxController taxController = new TaxController(tax, calculator);
        taxController.calculateSalesTotal(amount);
    }
    
    
    public void displaySalesTotal(float amount, float total){
        System.out.println("Amount: " + amount);
        System.out.println(" Total Sales: " + total);
    }
}
