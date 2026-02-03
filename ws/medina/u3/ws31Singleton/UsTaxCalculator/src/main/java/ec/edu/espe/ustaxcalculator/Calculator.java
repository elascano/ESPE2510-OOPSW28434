package ec.edu.espe.ustaxcalculator;

import java.util.Scanner;

/**
 *
 * @author Joseph B. Medina
 */

public class Calculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter tax percentage (e.g. 0,15): ");
        float taxValue = scanner.nextFloat();

        System.out.print("Enter sale amount: ");
        float amount = scanner.nextFloat();

        USTax tax = USTax.getInstance();
        tax.setTaxPercentage(taxValue);

        float total = tax.salesTotal(amount);

        System.out.println("Tax stored in Singleton: " + tax.getSingletonData());
        System.out.println("Total with tax: " + total);
    }
}
