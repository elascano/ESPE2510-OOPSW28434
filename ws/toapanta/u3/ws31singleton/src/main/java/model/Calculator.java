/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.TaxController;
import view.TaxView;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class Calculator {
    public static void main(String[] args) {
   
        USTax model = USTax.getInstance();

        TaxView view = new TaxView();

        TaxController controller = new TaxController(model, view);

        controller.setTaxRate(12f);

        controller.updateView(100f);
        
    }
}
