package ec.edu.espe.calculator.view;
import ec.edu.espe.operations.Operation; 
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */

public class TaxCalculator {
    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    private static String formatCurrency(float amount) {
        return "$" + df.format(amount);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        
        System.out.println("--- WELCOME TO THE INCOME TAX CALCULATOR BY MARYURI ---");
        
        System.out.print("Enter your average monthly salary: ");
        float monthlySalary = scanner.nextFloat(); 
        
        System.out.print("Enter your total Annual Personal Expenses: ");
        float annualSpentDeductible = scanner.nextFloat();
        
        System.out.print("Enter Number of Dependents: ");
        int dependents = scanner.nextInt(); 
        
        System.out.print("Enter Annual Withholdings (already paid): ");
        float annualWithholdings = scanner.nextFloat();
        
        scanner.close(); 
        
        float annualSalary = monthlySalary * 12.0F; 
        float taxableBase = Operation.calculateAnnualTaxableBase(annualSalary, annualSpentDeductible, dependents);
        float annualTaxRequired = Operation.calculateAnnualIncomeTax(taxableBase);
        float balance = annualWithholdings - annualTaxRequired;
        
        System.out.println("\n----------------------------------------------");
        System.out.println("Annual Tax REQUIRED: " + formatCurrency(annualTaxRequired));
        System.out.println("Annual Withholdings PAID: " + formatCurrency(annualWithholdings));
        System.out.println("----------------------------------------------");
 
        if (balance >= 0.01F) {
            System.out.println("RESULT: Balance in favor (Refund): " + formatCurrency(balance));
        } else if (balance <= -0.01F) {
            System.out.println("RESULT: Tax to pay this year is: " + formatCurrency(Math.abs(balance)));
        } else {
            System.out.println("RESULT: Final balance is zero.");
        }
        System.out.println("==============================================");
    }
}
