package ec.edu.espe.incometax.view;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
import ec.edu.espe.incometaxcalculation.IncomeTax;
import java.util.Scanner;

public class IncomeTaxCalculator {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        System.out.println("        INCOME TAX CALCULATOR BY KEVIN CHALAN");     
        double monthlySalary = IncomeTax.getValidatedDouble(scanner, "your  salary in DOLLARS");
        double monthlyExpenses = IncomeTax.getValidatedDouble(scanner, "your monthly expenses");
        int institutionalSector = IncomeTax.getValidatedSector(scanner);
        
        IncomeTax.showTaxResults(monthlySalary, institutionalSector, monthlyExpenses);
    
        scanner.close();
    }
}