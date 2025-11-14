package ec.edu.espe.taxecuador.view;

import ec.edu.espe.taxoperations.IncomeTaxCalculator;
import java.util.Scanner;

/**
 *
 * @author Arelys Otavalo
 */
public class Tax {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true; 

        System.out.println("=== Ecuador Income Tax Calculator 2024 ===");

        do {
            try {
                System.out.print("\nEnter monthly income: ");
                double monthlyIncome = scanner.nextDouble();
                
                System.out.print("Enter annual deductions: ");
                double annualDeductions = scanner.nextDouble();

                double annualTax = IncomeTaxCalculator.calculateAnnualTax(monthlyIncome, annualDeductions);
                
                System.out.println("\n------------------------------------------");
                System.out.println("Monthly income: $" + String.format("%.2f", monthlyIncome));
                System.out.println("Annual deductions: $" + String.format("%.2f", annualDeductions));
                System.out.println("Estimated annual income tax: $" + String.format("%.2f", annualTax));
                System.out.println("------------------------------------------");

                System.out.print("Do you want to calculate another tax? (yes/no): ");
                String response = scanner.next().trim().toLowerCase();

                if (response.equals("no") || response.equals("n")) {
                    continueCalculating = false;
                } else if (response.equals("yes") || response.equals("y")) {
                    continueCalculating = true; 
                } else {
                    System.out.println("Invalid option. Assuming NO and exiting.");
                    continueCalculating = false;
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("\nError: Please enter a valid number for income or deductions.");
                scanner.next(); 
                continueCalculating = true;
            }

        } while (continueCalculating);

        System.out.println("\nBye Bye ");
        scanner.close();
    }
}