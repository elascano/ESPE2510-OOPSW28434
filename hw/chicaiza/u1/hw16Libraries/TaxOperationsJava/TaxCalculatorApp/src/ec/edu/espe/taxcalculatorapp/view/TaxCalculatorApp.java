


package ec.edu.espe.taxcalculatorapp.view;

import ec.edu.espe.taxoperations.controller.TaxController;
import java.util.Scanner;
/**
 *
 * @author Daniel
 */

/**
 * TaxCalculatorApp
 * ----------------
 * Application that uses the ec.edu.espe.taxoperations library
 * to calculate monthly and annual income tax.
 */
public class TaxCalculatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaxController controller = new TaxController();

        System.out.println("=========================================");
        System.out.println("        ECUADOR INCOME TAX APP           ");
        System.out.println("=========================================");

        System.out.print("Enter monthly salary: ");
        float salary = scanner.nextFloat();

        System.out.print("Enter monthly expenses: ");
        float expenses = scanner.nextFloat();

        float monthlyTax = controller.calculateTax(salary, expenses);
        float annualTax = controller.calculateAnnualTax(salary, expenses);

        System.out.println("-----------------------------------------");
        System.out.println("Monthly tax to pay: $" + monthlyTax);
        System.out.println("Annual tax to pay:  $" + annualTax);
        System.out.println("=========================================");

        scanner.close();
    }
}
