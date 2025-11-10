package incometaxcalculator;
import java.util.Scanner;
import computings.TaxOperation;


/**
 *
 * @author Mateo Cevallos
 */

public class IncomeTaxCalculator {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Quick Tax Calculator");
        System.out.println("-------------------");
        
        System.out.print("Monthly salary ($): ");
        double salary = scanner.nextDouble();
        
        System.out.print("Annual expenses ($): ");
        double expenses = scanner.nextDouble();
        
        double tax = TaxOperation.calculateMonthlyTax(salary, expenses);
        
        System.out.println("\nResult:");
        System.out.printf("Monthly tax: $%.2f%n", tax);
        System.out.printf("Net monthly income: $%.2f%n", salary - tax);
        
        scanner.close();
    }
}
