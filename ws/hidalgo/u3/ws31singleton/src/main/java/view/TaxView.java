/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Mikae Hidalgo, Object Masters, @ESPE
 */
public class TaxView {
    public void printTaxDetails(float amount, float taxRate, float total) {
        System.out.println("------ DETALLES DEL CÁLCULO ------");
        System.out.println("Monto base: $" + amount);
        System.out.println("Impuesto aplicado: " + (taxRate * 100) + "%");
        System.out.println("Total a pagar: $" + total);
        System.out.println("----------------------------------");
    }
}
