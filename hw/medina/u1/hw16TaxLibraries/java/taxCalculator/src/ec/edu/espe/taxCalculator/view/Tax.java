package ec.edu.espe.taxCalculator.view;

import ec.edu.espe.taxOperations.EcuadorTaxCalculator;
import java.text.DecimalFormat;

/**
 *
 * @author Joseph B. Medina
 */
public class Tax {
    public static void main(String[] args) {

        DecimalFormat moneyFormat = new DecimalFormat("$###,###.00");
        
        double income;
        double deductions;
        double taxBase;
        double totalTax; 
        String bracket;
        
        income = 121343.76;
        deductions = 35000.74;
        
        taxBase = EcuadorTaxCalculator.calculateTaxableBase(income, deductions);
        
        bracket = EcuadorTaxCalculator.findTaxBracket(taxBase);
        
        totalTax = EcuadorTaxCalculator.calculateTotalTax(taxBase);

        System.out.println("=== ECUADOR INCOME TAX REPORT ===");
        System.out.println("Gross Income: " + moneyFormat.format(income));
        System.out.println("Allowable Deductions: " + moneyFormat.format(deductions));
        System.out.println("Taxable Base: " + moneyFormat.format(taxBase));
        System.out.println("-----------------------------------");
        System.out.println(bracket);
        System.out.println("Total Tax to Pay: " + moneyFormat.format(totalTax));
        System.out.println("===================================");
    }
}
