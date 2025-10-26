import java.util.Scanner;
public class MultiplicationTables {
    public static void main(String[] arguments) {
        Scanner input = new Scanner(System.in);
        System.out.println(" Welcome to the Multiplication Tables \n");
        while (true) {
            System.out.print(" Which multiplication table would you like to see? (1:12): ");
            if (!input.hasNextInt()) {
                System.out.println(" Invalid input. Please enter a number between 1 and 12.\n");
                input.next(); 
                continue;
            }
            int number = input.nextInt();
            if (number < 1 || number > 12) {
                System.out.println(" Please choose a number between 1 and 12.\n");
                continue;
            }
            printTable(number);
            while (true) {
                System.out.print("Do you want to see another table? (1 = Yes, 0 = No): ");
                
                if (!input.hasNextInt()) {
                    System.out.println("Invalid option. Enter 1 or 0.\n");
                    input.next(); 
                    continue;
                }
                int decision = input.nextInt();
                if (decision == 1) {
                    System.out.println();
                    break; 
                }
                if (decision == 0) {
                    System.out.println("\nGoodbye! Thanks for using the program.");
                    input.close();
                    return; 
                }
                System.out.println("Invalid option. Enter 1 or 0.\n");
            }
        }
    }
    public static void printTable(int number) {
        System.out.println("+" + "-".repeat(34) + "+");
        System.out.printf("|  Multiplication Table of %2d |\n", number);
        System.out.println("+" + "-".repeat(34) + "+");
        for (int i = 1; i <= 12; i++) {
            int result = number * i;
            System.out.printf("|   %2d x %2d = %3d               |\n", number, i, result);
        }
        System.out.println("+" + "-".repeat(34) + "+\n");
    }
}

