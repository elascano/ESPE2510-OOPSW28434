package ec.edu.espe.tax.controller;

import ec.edu.espe.tax.model.USTax;
import ec.edu.espe.tax.view.Calculator;

/**
 *
 * @author Pablo Collaguazo
 */
public class TaxController {
    private USTax usTax;
    private Calculator calculator;

    public TaxController(USTax usTax, Calculator calculator) {
        this.usTax = usTax;
        this.calculator = calculator;
    }
    
    public void calculateSalesTotal(float amount){
        float total;
        total = usTax.salesTotal( amount);
        calculator.displaySalesTotal(amount, total);
    }
        
}
