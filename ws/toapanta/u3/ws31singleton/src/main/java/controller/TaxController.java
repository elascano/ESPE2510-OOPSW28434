/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.USTax;
import view.TaxView;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class TaxController {
    private USTax model;
    private TaxView view;

    public TaxController(USTax model, TaxView view) {
        this.model = model;
        this.view = view;
    }

    public void setTaxRate(float rate) {
        model.setTaxPercentage(rate);
    }

    public void updateView(float amount) {
        float total = model.calculateSalesTotal(amount);
        view.printTaxDetails(amount, model.getTaxPercentage(), total);
    }
}
