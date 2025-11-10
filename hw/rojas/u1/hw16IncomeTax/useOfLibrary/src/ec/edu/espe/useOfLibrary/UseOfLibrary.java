package ec.edu.espe.useOfLibrary;

import java.util.Scanner;

public class UseOfLibrary {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("----- Welcome to Income Tax Calculation -----");

        // Instancia usando el nombre completamente calificado de TU librería
        ec.edu.espe.incomeTaxesCalculator.IncomeTaxesCalculator engine =
            new ec.edu.espe.incomeTaxesCalculator.IncomeTaxesCalculator();

        while (true) {
            try {
                System.out.print("Enter your monthly salary:\n");
                double monthly = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter your annual personal expenses:\n");
                double spent = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Enter your number of family dependents:\n");
                int deps = Integer.parseInt(sc.nextLine().trim());

                // Llamada al método compute de tu librería
                ec.edu.espe.incomeTaxesCalculator.IncomeTaxesCalculator.Result r =
                    engine.compute(monthly, spent, deps);

                System.out.printf("%nYour Tax to pay this year is: %.1f%n", r.annualTax);
                // System.out.printf("Monthly withholding: %.2f%n", r.monthlyWithholding);

            } catch (Exception e) {
                System.out.println("Input error: " + e.getMessage());
            }

            System.out.print("Would you like to calculate again? (s/n): ");
            String again = sc.nextLine().trim().toLowerCase();
            if (!again.equals("s")) {
                System.out.println("Goodbye!");
                break;
            }
        }
        sc.close();
    }
}
