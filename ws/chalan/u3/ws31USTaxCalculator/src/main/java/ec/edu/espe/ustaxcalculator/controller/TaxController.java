package ec.edu.espe.ustaxcalculator.controller;

import ec.edu.espe.ustaxcalculator.model.USTax;
import ec.edu.espe.ustaxcalculator.view.Calculator;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public class TaxController {
   private USTax uSTax;
    private Calculator calculator;

    public TaxController(USTax uSTax, Calculator calculator) {
        this.uSTax = uSTax;
        this.calculator = calculator;
    }
    
    public void calculateSalesToal(float amount){
        float total;
        total= uSTax.salesTotal(amount);
        calculator.displaySalesTotal(amount, total);
        
    }
}
