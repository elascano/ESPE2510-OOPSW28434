package ec.edu.espe.view;

import ec.edu.espe.incomeTax.Tax;
import java.util.Scanner;

/**
 *
 * @author Thais Santórum / Team 6 - Paradigm
 */
public class incomeTaxCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("- - - - - - - - - - - - -");
        System.out.println("  INCOME TAX CALCULATOR  ");
        System.out.println("- - - - - - - - - - - - -");

        // 
        System.out.print("Enter your monthly salary: ");
        double monthlySalary = scanner.nextDouble();

        System.out.print("Enter your annual deductible expenses: ");
        double deductibleExpenses = scanner.nextDouble();


        Tax calculator = new Tax(monthlySalary, deductibleExpenses);


        Tax.TaxResult result = calculator.calculateTax();


        System.out.println("\n- - - - - - - - - RESULTS - - - - - - - - -");
        System.out.printf("Annual income:             $%.2f%n", result.annualIncome);
        System.out.printf("Deductible expenses:       $%.2f%n", result.deductibleExpenses);
        System.out.printf("Taxable base:              $%.2f%n", result.taxableBase);
        System.out.printf("Annual tax:                $%.2f%n", result.annualTax);
        System.out.printf("Monthly tax:               $%.2f%n", result.monthlyTax);
        System.out.println("- - - - - - - - - - - - - - - - - - - - - -");

        scanner.close();
    }
}
