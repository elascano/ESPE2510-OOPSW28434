/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espe.ws29singleton.view;

import ec.edu.espe.ws29singleton.model.USTax;

/**
 *
 * @author Mateo Cevallos 
 */
public class Calculator {

    public static void main(String[] args) {
        
       float taxPercentage = 7F;
       float salesTotal = 10F;
       
        USTax.getInstance();
        salesTotal = USTax.salesTotal(taxPercentage);
        
        System.out.println("The total sale with a tax percentage of 7% is:" + salesTotal);
        
    }
}
