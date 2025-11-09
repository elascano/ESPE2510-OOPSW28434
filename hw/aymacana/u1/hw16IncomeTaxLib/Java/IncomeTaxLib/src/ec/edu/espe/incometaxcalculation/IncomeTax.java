package ec.edu.espe.incometaxcalculation;

/**
 *
 * @author Mateo Aymacaña @ESPE T.A.P(The Art of Programming)
 */
import java.util.Scanner;

public class IncomeTax {

    public static double contributionIESS(double monthlySalary, int institutionalSector) {
        double generalInsurance;
        double unemploymentInsurance;
        double totalContributionIESS;
        double baseCalculo;

        if (institutionalSector == 2 && monthlySalary > 2300) {
            baseCalculo = 2300;
        } else {
            baseCalculo = monthlySalary;
        }

        generalInsurance = baseCalculo * 0.0945;
        unemploymentInsurance = baseCalculo * 0.02;
        totalContributionIESS = generalInsurance + unemploymentInsurance;

        return totalContributionIESS;

    }

    public static double calculateTaxWithoutDeductions(double annualNetSalaryBeforeTax) {
        double tax = 0;

        if (annualNetSalaryBeforeTax <= 11902) {
            tax = 0;
        } else if (annualNetSalaryBeforeTax <= 15159) {
            tax = (annualNetSalaryBeforeTax - 11902) * 0.05;
        } else if (annualNetSalaryBeforeTax <= 19682) {
            tax = 163 + (annualNetSalaryBeforeTax - 15159) * 0.10;
        } else if (annualNetSalaryBeforeTax <= 26031) {
            tax = 615 + (annualNetSalaryBeforeTax - 19682) * 0.12;
        } else if (annualNetSalaryBeforeTax <= 34255) {
            tax = 1377 + (annualNetSalaryBeforeTax - 26031) * 0.15;
        } else if (annualNetSalaryBeforeTax <= 45407) {
            tax = 2611 + (annualNetSalaryBeforeTax - 34255) * 0.20;
        } else if (annualNetSalaryBeforeTax <= 60450) {
            tax = 4841 + (annualNetSalaryBeforeTax - 45407) * 0.25;
        } else if (annualNetSalaryBeforeTax <= 80605) {
            tax = 8602 + (annualNetSalaryBeforeTax - 60450) * 0.30;
        } else if (annualNetSalaryBeforeTax <= 107199) {
            tax = 14648 + (annualNetSalaryBeforeTax - 80605) * 0.35;
        } else {
            tax = 23956 + (annualNetSalaryBeforeTax - 107199) * 0.37;
        }

        return tax;
    }
    
    public static double getMarginalTaxRate(double income) {
        if (income <= 11902) {
            return 0.00;
        } else if (income <= 15159) {
            return 0.05;
        } else if (income <= 19682) {
            return 0.10;
        } else if (income <= 26031) {
            return 0.12;
        } else if (income <= 34255) {
            return 0.15;
        } else if (income <= 45407) {
            return 0.20;
        } else if (income <= 60450) {
            return 0.25;
        } else if (income <= 80605) {
            return 0.30;
        } else if (income <= 107199) {
            return 0.35;
        } else {
            return 0.37;
        }
    }

    public static double calculateRebaja(double annualNetSalaryBeforeTax, double monthlyExpenses) {
        double annualExpenses;
        double deductibleExpenses;
        double baseForRebaja;
        double marginalRate;
        double rebate;

        annualExpenses = monthlyExpenses * 12;
        deductibleExpenses = Math.min(annualExpenses, 5344);
        baseForRebaja = 0;
        marginalRate = getMarginalTaxRate(annualNetSalaryBeforeTax);

        if (annualNetSalaryBeforeTax > 26031 && annualNetSalaryBeforeTax <= 34255) {
            baseForRebaja = 6423.53;
        } else {
            baseForRebaja = deductibleExpenses;
        }

        rebate = baseForRebaja * marginalRate;
        return rebate;
    }

    public static double calculateIncomeTax(double annualNetSalaryBeforeTax, double monthlyExpenses) {
        double taxWithoutDeductions;
        double rebate;
        double finalTax;
        
        taxWithoutDeductions = calculateTaxWithoutDeductions(annualNetSalaryBeforeTax);
        rebate = calculateRebaja(annualNetSalaryBeforeTax, monthlyExpenses);
        finalTax = taxWithoutDeductions - rebate;
        
        return Math.max(finalTax, 0);
    }

    public static void showTaxResults(double monthlySalary, int institutionalSector, double monthlyExpenses) {
        double annualGrossSalary = monthlySalary * 12;
        double monthlyIESS = contributionIESS(monthlySalary, institutionalSector);
        double annualIESS = monthlyIESS * 12;
        double annualNetBeforeTax = annualGrossSalary - annualIESS;

        double incomeTax = calculateIncomeTax(annualNetBeforeTax, monthlyExpenses);
        double finalNetSalary = annualNetBeforeTax - incomeTax;

        System.out.println("\n==========================================");
        System.out.println("         INCOME TAX RESULTS");
        System.out.println("==========================================");

        System.out.printf("YOUR ANNUAL INCOME TAX IS: $%.2f%n", incomeTax);
        System.out.printf("Your annual net salary is: $%.2f%n", finalNetSalary);
        System.out.println("==========================================");
    }

    public static double getValidatedDouble(Scanner scanner, String message) {

        while (true) {
            try {
                System.out.print(message);
                double value = scanner.nextDouble();

                if (value < 0) {
                    System.out.println("ERROR: Value cannot be negative.");
                } else if (value == 0) {
                    System.out.println("ERROR: Value cannot be zero.");
                } else if (value > 20000) {
                    System.out.printf("ERROR: Salary cannot exceed $%.2f%n", 20000);
                } else {
                    return value;
                }

            } catch (Exception e) {
                System.out.println("ERROR: Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public static int getValidatedSector(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Employment sector (1=Public, 2=Private): ");
                int sector = scanner.nextInt();
                if (sector == 1 || sector == 2) {
                    return sector;
                } else {
                    System.out.println("ERROR: Sector must be 1 (Public) or 2 (Private).");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Please enter 1 or 2.");
                scanner.nextLine();
            }
        }
    }
}
