package chickeninjsoninjava.view;

/**
 *
 * @author Arelys Otavalo
 */

import chickeninjsoninjava.model.Chicken;
import chickeninjsoninjava.model.ChickenCoop;
import java.util.*;

public class ChickenInJsonInJava {
    private ChickenCoop coop;
    private Scanner scanner;

    public ChickenInJsonInJava() {
        coop = new ChickenCoop();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("\n Welcome to my Chicken Farm Simulator ");
        System.out.println("-----------------------------------------");
        System.out.println("1. Add Chicken");
        System.out.println("2. Visualize Chickens");
        System.out.println("3. Search Chicken by ID");
        System.out.println("4. Edit Chicken");
        System.out.println("5. Delete Chicken by ID");
        System.out.println("6. Exit");
        System.out.println("-----------------------------------------");
    }

    private int askChickensNumber() {
        while (true) {
            try {
                System.out.print("How many chickens do you want to save? ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private Chicken askChickenInformation() {
        System.out.println("\nNew Chicken Information");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        int age;
        while (true) {
            try {
                System.out.print("Age in years: ");
                age = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for age.");
            }
        }

        boolean isMolting = false;
        while (true) {
            System.out.print("Is molting? (y/n): ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("y")) {
                isMolting = true;
                break;
            } else if (input.equals("n")) {
                isMolting = false;
                break;
            } else {
                System.out.println("Please enter 'y' or 'n'.");
            }
        }

        return new Chicken(name, color, age, isMolting);
    }

    public void addChicken() {
        int amount = askChickensNumber();
        for (int i = 0; i < amount; i++) {
            Chicken chicken = askChickenInformation();
            coop.addChicken(chicken);
            System.out.println("Chicken '" + chicken.getName() + "' added with ID: " + chicken.getId());
        }
        System.out.println("Chickens saved successfully!");
    }

    public void visualizeAllChickens() {
        ArrayList<Chicken> list = coop.getChickens();
        if (list.isEmpty()) {
            System.out.println("There are no chickens to display.");
            return;
        }

        System.out.printf("\nIt found %d chickens:\n\n", list.size());
        System.out.println("+----+----------+----------+-----+---------+");
        System.out.println("| ID |   Name   |  Color   | Age | Molting |");
        System.out.println("+----+----------+----------+-----+---------+");
        for (Chicken c : list) {
            System.out.printf("| %-2s | %-8s | %-8s | %-3d | %-7s |\n",
                    c.getId(), c.getName(), c.getColor(), c.getAge(), c.isMolting() ? "Yes" : "No");
        }
        System.out.println("+----+----------+----------+-----+---------+");
    }

    public void searchChicken() {
        System.out.print("Enter the ID of the chicken to search: ");
        String id = scanner.nextLine();
        Chicken c = coop.findChickenById(id);
        if (c != null) {
            System.out.println("\nChicken found:\n" + c);
        } else {
            System.out.println("Chicken with ID '" + id + "' not found.");
        }
    }

    public void editChicken() {
        System.out.print("Enter the ID of the chicken to edit: ");
        String id = scanner.nextLine();
        Chicken c = coop.findChickenById(id);

        if (c == null) {
            System.out.println("Chicken with ID '" + id + "' not found.");
            return;
        }

        String newName = c.getName();
        String newColor = c.getColor();
        int newAge = c.getAge();
        boolean newMolting = c.isMolting();

        while (true) {
            System.out.println("\n--- Editing Chicken ID: " + c.getId() + " (" + c.getName() + ") ---");
            System.out.println("1. Name: " + newName);
            System.out.println("2. Color: " + newColor);
            System.out.println("3. Age: " + newAge);
            System.out.println("4. Molting: " + (newMolting ? "Yes" : "No"));
            System.out.println("5. Save Changes and Exit");
            System.out.println("6. Cancel and Exit");
            System.out.print("Choose an option (1-6): ");

            String opt = scanner.nextLine();
            switch (opt) {
                case "1":
                    System.out.print("New Name: ");
                    newName = scanner.nextLine();
                    break;
                case "2":
                    System.out.print("New Color: ");
                    newColor = scanner.nextLine();
                    break;
                case "3":
                    System.out.print("New Age: ");
                    try {
                        newAge = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                    }
                    break;
                case "4":
                    System.out.print("Is molting? (y/n): ");
                    String input = scanner.nextLine().toLowerCase();
                    newMolting = input.equals("y");
                    break;
                case "5":
                    if (coop.editChicken(id, newName, newColor, newAge, newMolting)) {
                        System.out.println("Chicken with ID " + id + " updated successfully!");
                    } else {
                        System.out.println("Error updating chicken.");
                    }
                    return;
                case "6":
                    System.out.println("Editing cancelled.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void deleteChicken() {
        System.out.print("Enter the ID of the chicken to delete: ");
        String id = scanner.nextLine();
        if (coop.deleteChicken(id)) {
            System.out.println("Chicken with ID " + id + " deleted successfully!");
        } else {
            System.out.println("Chicken with ID '" + id + "' not found.");
        }
    }

    public void run() {
        while (true) {
            showMenu();
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();
            switch (option) {
                case "1": addChicken(); break;
                case "2": visualizeAllChickens(); break;
                case "3": searchChicken(); break;
                case "4": editChicken(); break;
                case "5": deleteChicken(); break;
                case "6":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ChickenInJsonInJava app = new ChickenInJsonInJava();
        app.run();
    }
}
