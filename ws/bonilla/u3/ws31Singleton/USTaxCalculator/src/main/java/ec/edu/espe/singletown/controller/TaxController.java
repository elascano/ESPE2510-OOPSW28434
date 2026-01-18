package ec.edu.espe.singletown.controller;

import ec.edu.espe.singletown.model.USTax;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class TaxController {

    private USTax tax;

    public TaxController() {
        tax = USTax.getInstance();
    }

    public void setTaxPercentage(float percentage) {
        tax.setTaxPercentage(percentage);
    }

    public String getTaxInfo() {
        return tax.getSingletonData();
    }

    public float getTotal(float sale) {
        return tax.salesTotal(sale);
    }
}