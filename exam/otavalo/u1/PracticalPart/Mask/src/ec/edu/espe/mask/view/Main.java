package ec.edu.espe.mask.view;
import ec.edu.espe.mask.controller.MaskController;
import ec.edu.espe.mask.model.Mask;
import java.util.*;

/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming, @ESPE
 */


public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final MaskController controller = new MaskController();

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        System.out.println("\n===== STUDENT MENU =====");
        System.out.println("1. Show all masks");
        System.out.println("2. Add new mask");
        System.out.println("3. Edit mask");
        System.out.println("4. Delete mask");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1" ->
                showMasks();
            case "2" ->
                addMask();
            case "3" ->
                editMask();
            case "4" ->
                deleteMask();
            case "5" ->
                System.exit(0);
            default ->
                menu();
        }
    }

    private static void showMasks() {
        List<Mask> masks = controller.getAllMasks();

        for (Mask mask : masks) {
            System.out.println(
                    "ID: " + mask.getMaskId()
                    + ", Name: " + mask.getMaskName()
                    + ", Grades: " + mask.getPricesList()
                    + ", Average: " + mask.getAverageGrade()
            );
        }
        menu();
    }

    private static void addMask() {
        System.out.print("Mask name: ");
        String name = scanner.nextLine();

        List<Double> prices = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            while (true) {
                try {
                    System.out.print("Enter price #" + i + " (0 to 50): ");
                    double price = Double.parseDouble(scanner.nextLine());

                    if (price >= 0 && price <= 50) {
                        prices.add(price);
                        break; 
                    } else {
                        System.out.println(" Price must be between 0 and 50. Try again.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println(" Invalid input. Enter a valid number.");
                }
            }
        }

        Mask mask = controller.addMask(name, prices);
        System.out.println("Mask added: ID " + mask.getMaskId());

        menu();
    }

    private static void editMask() {
        System.out.print("Enter mask ID to edit: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("New name (leave blank): ");
        String name = scanner.nextLine();

        List<Double> prices = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            System.out.print("Enter new price #" + i + " (leave blank): ");
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                prices.add(Double.parseDouble(input));
            }
        }

        if (controller.editMask(id, name, prices)) {
            System.out.println("Mask updated!");
        } else {
            System.out.println("Mask not found.");
        }

        menu();
    }

    private static void deleteMask() {
        System.out.print("Enter mask ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (controller.deleteMask(id)) {
            System.out.println("Mask deleted.");
        } else {
            System.out.println("Mask not found.");
        }

        menu();
    }

}


