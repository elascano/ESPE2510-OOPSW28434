package ec.edu.espe.ustaxcalculator.view;

import ec.edu.espe.ustaxcalculator.controller.TaxController;
import ec.edu.espe.ustaxcalculator.model.USTax;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public class Calculator {
    public static void main(String[] args) {
        USTax tax = USTax.getInstance();
        tax.setTaxPercentage(13.5f);
       float amount = 120;
       
        
        System.out.println("ustax: " + tax.getSingletonData());
        System.out.println("Total: " + tax.salesTotal(100));
        
      Calculator calculator = new Calculator();
      TaxController taxController = new TaxController(tax, calculator);
      taxController.calculateSalesToal(amount);
    }
    
    public void displaySalesTotal(float  amount, float total){
        System.out.println("amount: " + amount);
        System.out.println("total sales: "+ total);
  
    }
}