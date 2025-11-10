


package ec.edu.espe.taxoperations.controller;

import ec.edu.espe.taxoperations.model.Tax;
/**
 *
 * @author Daniel
 */
public class TaxController {

    /**
     * Calculates the tax given salary and expenses.
     * @param salary monthly income
     * @param expenses declared expenses
     * @return computed tax value
     */
    public float calculateTax(float salary, float expenses) {
        Tax tax = new Tax(salary, expenses);
        return tax.computeTax();
    }

    /**
     * Calculates the annual tax (based on 12 months).
     * @param salary monthly salary
     * @param expenses monthly expenses
     * @return annual tax
     */
    public float calculateAnnualTax(float salary, float expenses) {
        float monthlyTax = calculateTax(salary, expenses);
        return monthlyTax * 12;
    }
}