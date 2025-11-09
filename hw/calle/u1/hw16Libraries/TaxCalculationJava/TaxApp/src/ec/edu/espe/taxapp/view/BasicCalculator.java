package ec.edu.espe.taxapp.view; 

import ec.edu.espe.incometax.operations.TaxOperation;
import java.util.Scanner;

public class BasicCalculator {
    public static void main(String[] args) {
        TaxOperation taxCalculator = new TaxOperation();

        System.out.println("\n=============================================");
        System.out.println("  INCOME TAX CALCULATION  - ECUADOR");
        System.out.println("=============================================");
        
        try (Scanner scanner = new Scanner(System.in)) { 
            
            System.out.println("\n[1] ANNUAL INCOME DATA");
            System.out.println("---------------------------------------------");
            System.out.print("  Enter Gross Monthly Salary (USD): ");
            double monthlySalary = scanner.nextDouble();
            
            System.out.print("  Enter Total Projected Personal Expenses: ");
            double personalExpenses = scanner.nextDouble();
            
            System.out.print("  Enter Number of Dependents: ");
            int dependents = scanner.nextInt(); 
            
            System.out.print("  Enter Annual Income Tax Withheld (Paid): ");
            double annualWithholdings = scanner.nextDouble(); 

            double annualGrossIncome = monthlySalary * 12.0;
            double annualTaxableBase = taxCalculator.calculateAnnualTaxableBase(monthlySalary); 
            double annualTaxDue = taxCalculator.calculateAnnualIncomeTax(annualTaxableBase);
            
            double taxRebateAmount = taxCalculator.calculateTaxRebate(personalExpenses, dependents, annualGrossIncome); 

            double finalAnnualTax = annualTaxDue - taxRebateAmount;
            if (finalAnnualTax < 0) {
                finalAnnualTax = 0; 
            }
            
            double finalBalance = finalAnnualTax - annualWithholdings;

     

            if (finalBalance > 0) {
                System.out.printf("**TAX PENDING TO PAY:** $%,.2f%n", finalBalance);
            } else if (finalBalance < 0) {
                System.out.printf("**BALANCE IN FAVOR (REFUND):** $%,.2f%n", Math.abs(finalBalance));
            } else {
                System.out.println("**NO BALANCE DUE (PAID EXACTLY)**");
            }
            
            System.out.println("=============================================");

        } catch (Exception e) {
            System.err.println("\n[ERROR] Invalid input. Please enter valid numeric data.");
        }
    }
}