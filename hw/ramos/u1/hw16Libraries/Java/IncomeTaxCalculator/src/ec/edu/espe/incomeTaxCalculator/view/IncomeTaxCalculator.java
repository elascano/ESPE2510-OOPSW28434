package ec.edu.espe.incomeTaxCalculator.view;

/**
 *
 * @author Paulo Ramos
 */

import ec.espe.edu.incomeTaskOperation.IncomeTax;
import java.util.Scanner;

public class IncomeTaxCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IncomeTax taxCalculator = new IncomeTax();

        System.out.println("=== Income Tax Calculator ===\n");

        System.out.print("Enter your monthly salary (USD): ");
        double salary = scanner.nextDouble();

        System.out.print("Enter your monthly deductible expenses (USD): ");
        double expenses = scanner.nextDouble();

        double monthlyTax = taxCalculator.calculateMonthlyTax(salary, expenses);
        double annualTax = monthlyTax * 12;

        double annualIncome = salary * 12;
        String bracketInfo = taxCalculator.findTaxBracket(annualIncome - expenses * 12);

        System.out.println("\n-------------------------------------");
        System.out.println(bracketInfo);
        System.out.printf("Estimated monthly tax: $%.2f%n", monthlyTax);
        System.out.printf("Estimated annual tax:  $%.2f%n", annualTax);
        System.out.println("-------------------------------------");

    }
}
