package chickenfarmsimulator.view;

import chickenfarmsimulator.controller.FarmController;
import chickenfarmsimulator.model.Chicken;
import chickenfarmsimulator.model.ChickenCoop;
import java.util.List;
import java.util.Scanner;

public class FarmView {
    private FarmController controller;
    private Scanner scanner;
    
    public FarmView(FarmController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }
    
    public void showMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("          CHICKEN FARM SIMULATOR");
        System.out.println("=".repeat(50));
        System.out.println("Coops: " + controller.getCoopCount() + " | Chickens: " + controller.getChickenCount());
        System.out.println("-".repeat(50));
        System.out.println("1. Create coop");
        System.out.println("2. Add chicken to coop");
        System.out.println("3. View all coops and chickens");
        System.out.println("4. Edit chicken");
        System.out.println("5. Delete chicken");
        System.out.println("6. Perform chicken action");
        System.out.println("7. Exit");
        System.out.println("-".repeat(50));
    }
    
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    public void createCoop() {
        System.out.println("\n--- CREATE COOP ---");
        try {
            int coopId = Integer.parseInt(getInput("Coop ID: "));
            if (controller.createCoop(coopId)) {
                System.out.println("Coop created successfully!");
            } else {
                System.out.println("Error: A coop with that ID already exists");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be an integer");
        }
    }
    
    public void addChicken() {
        System.out.println("\n--- ADD CHICKEN ---");
        try {
            int coopId = Integer.parseInt(getInput("Coop ID: "));
            int chickenId = Integer.parseInt(getInput("Chicken ID: "));
            String name = getInput("Chicken name: ");
            String color = getInput("Chicken color: ");
            int age = Integer.parseInt(getInput("Chicken age (months): "));
            String moltingInput = getInput("Is molting? (y/n): ").toLowerCase();
            boolean isMolting = moltingInput.equals("y");
            
            if (controller.addChicken(coopId, chickenId, name, color, age, isMolting)) {
                System.out.println("Chicken added successfully!");
            } else {
                System.out.println("Error: Could not add chicken (coop not found or duplicate ID)");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: IDs and age must be integers");
        }
    }
    
    public void showAllCoops() {
        System.out.println("\n--- COOPS AND CHICKENS ---");
        List<ChickenCoop> coops = controller.getAllCoops();
        
        if (coops.isEmpty()) {
            System.out.println("No coops registered.");
            return;
        }
        
        for (ChickenCoop coop : coops) {
            System.out.println("\nCOOP ID: " + coop.getId());
            System.out.println("Number of chickens: " + coop.getChickens().size());
            
            if (coop.getChickens().isEmpty()) {
                System.out.println("No chickens in this coop");
                continue;
            }
            
            // Print table header
            System.out.println("+----+-----------------+------------+-------+---------+");
            System.out.println("| ID | Name            | Color      | Age   | Molting |");
            System.out.println("+----+-----------------+------------+-------+---------+");
            
            for (Chicken chicken : coop.getChickens()) {
                String moltingStatus = chicken.isMolting() ? "Yes" : "No";
                System.out.printf("| %-2d | %-15s | %-10s | %-5d | %-7s |\n",
                    chicken.getId(), chicken.getName(), chicken.getColor(), 
                    chicken.getAge(), moltingStatus);
            }
            
            System.out.println("+----+-----------------+------------+-------+---------+");
        }
    }
    
    public void editChicken() {
        System.out.println("\n--- EDIT CHICKEN ---");
        try {
            int coopId = Integer.parseInt(getInput("Coop ID: "));
            int chickenId = Integer.parseInt(getInput("Chicken ID to edit: "));
            
            ChickenCoop coop = controller.getCoop(coopId);
            if (coop == null) {
                System.out.println("Error: Coop not found");
                return;
            }
            
            Chicken chicken = coop.getChicken(chickenId);
            if (chicken == null) {
                System.out.println("Error: Chicken not found");
                return;
            }
            
            System.out.println("\nEditing chicken: " + chicken.getName());
            String name = getInput("New name (" + chicken.getName() + "): ");
            if (name.isEmpty()) name = chicken.getName();
            
            String color = getInput("New color (" + chicken.getColor() + "): ");
            if (color.isEmpty()) color = chicken.getColor();
            
            String ageInput = getInput("New age (" + chicken.getAge() + "): ");
            int age = ageInput.isEmpty() ? chicken.getAge() : Integer.parseInt(ageInput);
            
            String moltingInput = getInput("Is molting? (y/n) [" + (chicken.isMolting() ? "y" : "n") + "]: ").toLowerCase();
            boolean isMolting = moltingInput.isEmpty() ? chicken.isMolting() : moltingInput.equals("y");
            
            if (controller.updateChicken(coopId, chickenId, name, color, age, isMolting)) {
                System.out.println("Chicken updated successfully!");
            } else {
                System.out.println("Error: Could not update chicken");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: IDs and age must be integers");
        }
    }
    
    public void deleteChicken() {
        System.out.println("\n--- DELETE CHICKEN ---");
        try {
            int coopId = Integer.parseInt(getInput("Coop ID: "));
            int chickenId = Integer.parseInt(getInput("Chicken ID to delete: "));
            
            String confirm = getInput("Are you sure you want to delete this chicken? (y/n): ").toLowerCase();
            if (confirm.equals("y")) {
                if (controller.deleteChicken(coopId, chickenId)) {
                    System.out.println("Chicken deleted successfully!");
                } else {
                    System.out.println("Error: Could not delete chicken (coop or chicken not found)");
                }
            } else {
                System.out.println("Operation cancelled");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: IDs must be integers");
        }
    }
    
    public void performChickenAction() {
        System.out.println("\n--- CHICKEN ACTIONS ---");
        try {
            int coopId = Integer.parseInt(getInput("Coop ID: "));
            int chickenId = Integer.parseInt(getInput("Chicken ID: "));
            
            System.out.println("\nAvailable actions:");
            System.out.println("1. Cluck");
            System.out.println("2. Wander");
            System.out.println("3. Eat");
            System.out.println("4. Drink");
            System.out.println("5. Poop");
            System.out.println("6. Lay Egg");
            
            int actionChoice = Integer.parseInt(getInput("Select an action (1-6): "));
            
            System.out.println("\n" + "=".repeat(30));
            if (controller.performAction(coopId, chickenId, actionChoice)) {
                System.out.println("Action performed successfully!");
            } else {
                System.out.println("Error: Could not perform action (coop or chicken not found)");
            }
            System.out.println("=".repeat(30));
        } catch (NumberFormatException e) {
            System.out.println("Error: IDs must be integers");
        }
    }
    
    public void run() {
        System.out.println("Starting Chicken Farm Simulator...");
        
        while (true) {
            showMenu();
            String choice = getInput("Select an option (1-7): ");
            
            switch (choice) {
                case "1":
                    createCoop();
                    break;
                case "2":
                    addChicken();
                    break;
                case "3":
                    showAllCoops();
                    break;
                case "4":
                    editChicken();
                    break;
                case "5":
                    deleteChicken();
                    break;
                case "6":
                    performChickenAction();
                    break;
                case "7":
                    System.out.println("Thank you for using Chicken Farm Simulator!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Error: Invalid option. Please select 1-7.");
            }
            
            System.out.print("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
}