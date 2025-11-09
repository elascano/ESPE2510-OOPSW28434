package es.edu.espe.incometax.view;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
import ec.edu.espe.incometaxcalculation.IncomeTax;
import java.util.Scanner;

public class IncomeTaxCalculator {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        
        System.out.println("==========================================");
        System.out.println("        INCOME TAX CALCULATOR");
        System.out.println("==========================================");
        
        double monthlySalary = IncomeTax.getValidatedDouble(scanner, "Enter your monthly salary: $");
        double monthlyExpenses = IncomeTax.getValidatedDouble(scanner, "Enter your monthly expenses: $");
        int institutionalSector = IncomeTax.getValidatedSector(scanner);
        
        IncomeTax.showTaxResults(monthlySalary, institutionalSector, monthlyExpenses);
    
        scanner.close();
    }
}
