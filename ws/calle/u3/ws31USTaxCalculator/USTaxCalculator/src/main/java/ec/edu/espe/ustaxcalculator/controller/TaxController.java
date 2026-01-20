package ec.edu.espe.ustaxcalculator.controller;

import ec.edu.espe.ustaxcalculator.model.USTax;
import ec.edu.espe.ustaxcalculator.view.Calculator;

/**
 *
 * @author Emily Calle, @ESPE
 */
public class TaxController {
    private USTax usTax;
    private Calculator calculator;
    private double lastTotal;

    public TaxController(USTax usTax, Calculator calculator) {
        this.usTax = usTax;
        this.calculator = calculator;
    }

    public void calculateSalesTotal(double amount) {
        this.lastTotal = usTax.salesTotal(amount);
        calculator.display();
    }

    public double getLastTotal() {
        return lastTotal;
    }
}