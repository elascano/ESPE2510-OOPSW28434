package chickenfarmsimulatorjava.view;

/**
 *
 * @author Gabriel
 */

import java.util.Scanner;
import chickenfarmsimulatorjava.model.ChickenFarm;

public class ChickenFarmSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChickenFarm farm = new ChickenFarm();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Chicken Farm Simulator ===");
            System.out.println("1) Add Chicken");
            System.out.println("2) View All Chickens");
            System.out.println("3) Remove Chicken");
            System.out.println("4) Exit");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter chicken name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter chicken age: ");
                    int age;
                    try {
                        age = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid age!");
                        break;
                    }

                    System.out.print("Is molting (true/false): ");
                    boolean molting = Boolean.parseBoolean(scanner.nextLine());

                    farm.addChicken(name, age, molting);
                    break;

                case "2":
                    farm.showAll();
                    break;

                case "3":
                    System.out.print("Enter ID to remove: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        farm.removeChicken(id);
                    } catch (NumberFormatException e) {
                        System.out.println("️ Invalid ID!");
                    }
                    break;

                case "4":
                    System.out.println(" Exiting...");
                    exit = true;
                    break;

                default:
                    System.out.println("️ Invalid option!");
            }
        }
        scanner.close();
    }
}
