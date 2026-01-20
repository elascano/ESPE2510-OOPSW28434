package ec.espe.edu.taxcalculator.view;

import ec.espe.edu.incometaxcalculation.IncomeTax;
import java.util.Scanner;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class TaxCalculation {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("-----Welcome to Income Tax Calculation-----");
        
        float monthlySalary;
        double annualPersonalExpenses;
        int numberOfFamilyDeficits;
        double incomeTaxValue; 
        
        System.out.println("Enter your monthly salary: ");
        monthlySalary = read.nextFloat();
        
        System.out.println("Enter your annual personal expenses: ");
        annualPersonalExpenses = read.nextDouble();
        
        System.out.println("Enter your number of family deficits: ");
        numberOfFamilyDeficits = read.nextInt();
        
        IncomeTax taxCalculator = IncomeTax.getInstance();
        incomeTaxValue = taxCalculator.incomeTaxCalculation(monthlySalary, annualPersonalExpenses, numberOfFamilyDeficits);
        
        System.out.println("Your Tax to pay this year is: " + incomeTaxValue);
    }    
}