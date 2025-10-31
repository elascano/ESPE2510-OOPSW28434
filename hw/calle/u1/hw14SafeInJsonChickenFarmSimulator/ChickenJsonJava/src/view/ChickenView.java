package view;

import controller.ChickenController;
import model.Chicken;
import java.util.List;
import java.util.Scanner;

public class ChickenView {
    private final ChickenController controller = new ChickenController();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        int option;
        do {
            System.out.println("==== Chicken Farm Menu ====");
            System.out.println("1. Insert Chicken");
            System.out.println("2. List Chickens");
            System.out.println("3. Delete Chicken");
            System.out.println("4. Update Chicken");
            System.out.println("5. Find Chicken");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                option = -1;
            }

            switch (option) {
                case 1 -> insertChicken();
                case 2 -> listChickens();
                case 3 -> deleteChicken();
                case 4 -> updateChicken();
                case 5 -> findChicken();
                case 0 -> System.out.println("Exiting...");
                default -> {
                    if (option != -1) System.out.println("Invalid option");
                }
            }
        } while (option != 0);
    }

    private void insertChicken() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        System.out.print("Molting (true/false): ");
        boolean molting = scanner.nextBoolean();
        
        Chicken chicken = new Chicken(name, color, age, molting); 
        controller.addChicken(chicken);
        System.out.println("Chicken added with ID: " + chicken.getId());
    }

    private void listChickens() {
        List<Chicken> chickens = controller.readChickens();
        if (chickens.isEmpty()) {
            System.out.println("No chickens found.");
        } else {
            System.out.printf("%-5s %-15s %-10s %-5s %-10s%n", "ID", "Name", "Color", "Age", "Molting");
            System.out.println("----- --------------- ---------- ----- ----------");
            for (Chicken chicken : chickens) {
                System.out.printf("%-5d %-15s %-10s %-5d %-10b%n",
                        chicken.getId(),
                        chicken.getName(),
                        chicken.getColor(),
                        chicken.getAge(),
                        chicken.isMolting());
            }
        }
    }

    private void deleteChicken() {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();
        boolean deleted = controller.deleteChicken(id);
        System.out.println(deleted ? "Chicken deleted." : "Chicken not found.");
    }

    private void updateChicken() {
        System.out.print("Enter ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Chicken existing = controller.findChicken(id);
        if (existing == null) {
            System.out.println("Chicken not found.");
            return;
        }
        System.out.println("Updating Chicken ID: " + existing.getId());
        
        System.out.print("New name (" + existing.getName() + "): ");
        String name = scanner.nextLine();
        
        System.out.print("New color (" + existing.getColor() + "): ");
        String color = scanner.nextLine();
        
        System.out.print("New age (" + existing.getAge() + "): ");
        int age = scanner.nextInt();
        
        System.out.print("Molting (true/false) (" + existing.isMolting() + "): ");
        boolean molting = scanner.nextBoolean();
        
        Chicken updated = new Chicken( name, color, age, molting);
        controller.updateChicken(updated);
        System.out.println("Chicken updated.");
    }

    // findChicken MODIFICADO para mostrar en tabla
    private void findChicken() {
        System.out.print("Enter ID to find: ");
        int id = scanner.nextInt();
        Chicken chicken = controller.findChicken(id);
        
        if (chicken != null) {
            System.out.println("Chicken Found:");
            // Encabezados de la tabla
            System.out.printf("%-5s %-15s %-10s %-5s %-10s%n", "ID", "Name", "Color", "Age", "Molting");
            System.out.println("----- --------------- ---------- ----- ----------");
            // Datos del pollo encontrado
            System.out.printf("%-5d %-15s %-10s %-5d %-10b%n",
                    chicken.getId(),
                    chicken.getName(),
                    chicken.getColor(),
                    chicken.getAge(),
                    chicken.isMolting());
        } else {
            System.out.println("Chicken not found.");
        }
    }
}