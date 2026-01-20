
package ec.edu.espe.ustaxcalculator.controller;

import ec.edu.espe.ustaxcalculator.model.USTax;
import ec.edu.espe.ustaxcalculator.view.Calculator;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class TaxController {
    private USTax usTax;
    private Calculator calculator;

    public TaxController(USTax usTax, Calculator calculator) {
        this.usTax = usTax;
        this.calculator = calculator;
    }
    
    public void CalculateSalesTotal(float amount){
        float total;
        total = usTax.salesTotal(amount);
        calculator.displaySalesTotal(amount, total);
        
    }
    
}
