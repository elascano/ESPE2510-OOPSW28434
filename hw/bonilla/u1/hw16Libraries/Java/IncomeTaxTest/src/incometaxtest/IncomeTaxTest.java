package incometaxtest;

import ec.edu.espe.incometax.IncomeTax;
import java.util.Scanner;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class IncomeTaxTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("====== INCOME TAX CALCULATOR ======");
            System.out.println("1. Calculate Income Tax");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            String option = input.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter your monthly salary: $");
                    double salary = readDouble(input);

                    System.out.print("Enter your monthly expenses: $");
                    double expenses = readDouble(input);

                    double tax = IncomeTax.calculateTax(salary, expenses);
                    double annualIncome = salary * 12;
                    double annualExpenses = expenses * 12;
                    double taxableIncome = annualIncome - annualExpenses;

                    System.out.println("------------------------------------");
                    System.out.println("Monthly Salary: $" + salary);
                    System.out.println("Monthly Expenses: $" + expenses);
                    System.out.println("Annual Income: $" + annualIncome);
                    System.out.println("Annual Expenses: $" + annualExpenses);
                    System.out.println("Taxable Income: $" + taxableIncome);
                    System.out.println("Your annual income tax is: $" + tax);
                    System.out.println("------------------------------------\n");
                    break;

                case "2":
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.\n");
                    break;
            }
        }

        input.close();
    }

    // Helper method to safely read double values
    private static double readDouble(Scanner input) {
        while (true) {
            try {
                return Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please try again: $");
            }
        }
    }
    
}
