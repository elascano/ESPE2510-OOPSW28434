package view;
import java.util.ArrayList;
import java.util.Scanner;
import model.ChickenCoop;
import model.Chicken;
import model.JSONFileManager;

import model.ChickenCoop;

import model.ChickenCoop;
/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */

public class ChickenFarmSimulator {
    private static ArrayList<ChickenCoop> coops = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Welcome to the Chicken Farm Simulator");
        
        try {
            coops = JSONFileManager.loadFromFile();
        } catch (Exception e) {
            System.out.println("Error loading initial data: " + e.getMessage());
        }
        
        showMainMenu();
    }
    
    private static void showMainMenu() {
        int option;
        do {
            System.out.println("\n=== CHICKEN FARM MAIN MENU ===");
            System.out.println("1. Farm Management (Coops and Chickens)");
            System.out.println("2. JSON File Operations");
            System.out.println("0. Exit (Save and Exit)");
            System.out.print("Select an option: ");
            
            try {
                option = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (option) {
                    case 1 -> showFarmMenu();
                    case 2 -> showJsonMenu();
                    case 0 -> {
                        JSONFileManager.saveToFile(coops);
                        System.out.println("Goodbye! Thanks for using Chicken Farm Simulator!");
                    }
                    default -> System.out.println("Invalid option! Please select 1, 2, or 0.");
                }
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed. Please try again.");
                scanner.nextLine();
                option = -1; 
            }
        } while (option != 0);
        
        scanner.close();
    }
    
    private static void showFarmMenu() {
        int option;
        do {
            System.out.println("\n=== FARM MANAGEMENT ===");
            System.out.println("1. Create Chicken Coop");
            System.out.println("2. Add Chicken to Coop");
            System.out.println("3. List All Coops");
            System.out.println("4. List Chickens in Coop");
            System.out.println("5. Remove Chicken from Coop");
            System.out.println("6. Make Chickens Do Stuff");
            System.out.println("7. Remove Coop");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine();
                
                switch (option) {
                    case 1 -> createChickenCoop();
                    case 2 -> addChickenToCoop();
                    case 3 -> listAllCoops();
                    case 4 -> listChickensInCoop();
                    case 5 -> removeChickenFromCoop();
                    case 6 -> makeChickensDoStuff();
                    case 7 -> removeCoop();
                    case 0 -> System.out.println("Returning...");
                    default -> System.out.println("Invalid option! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed. Please try again.");
                scanner.nextLine();
                option = -1;
            }
        } while (option != 0);
    }
    
    private static void showJsonMenu() {
        int option;
        do {
            System.out.println("\n=== JSON OPERATIONS ===");
            System.out.println("1. Save Data to JSON File");
            System.out.println("2. Load Data from JSON File");
            System.out.println("3. Display JSON File Content");
            System.out.println("4. Update Chicken Data");
            System.out.println("5. Delete Chicken from JSON");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine();
                
                switch (option) {
                    case 1 -> JSONFileManager.saveToFile(coops);
                    case 2 -> coops = JSONFileManager.loadFromFile();
                    case 3 -> JSONFileManager.displayFileData();
                    case 4 -> updateChickenData();
                    case 5 -> deleteChickenFromJSON();
                    case 0 -> System.out.println("Returning...");
                    default -> System.out.println("Invalid option! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed. Please try again.");
                scanner.nextLine();
                option = -1;
            }
        } while (option != 0);
    }

    private static void createChickenCoop() {
        System.out.println("\n--- CREATE CHICKEN COOP ---");
        
        int coopId = 0;
        while (true) {
            try {
                System.out.print("Enter Coop ID: ");
                coopId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Coop ID.");
                scanner.nextLine();
            }
        }

        for (ChickenCoop coop : coops) {
            if (coop.getId() == coopId) {
                System.out.println("Coop with ID " + coopId + " already exists!");
                return;
            }
        }

        System.out.print("Enter Coop description: ");
        String description = scanner.nextLine();

        ChickenCoop newCoop = new ChickenCoop(coopId, description);
        coops.add(newCoop);
        System.out.println("Coop created successfully!");
    }
    
    private static void addChickenToCoop() {
        if (coops.isEmpty()) {
            System.out.println("No coops available. Please create a coop first.");
            return;
        }
        
        System.out.println("\n--- ADD CHICKEN TO COOP ---");
        
        System.out.println("Available coops:");
        for (ChickenCoop coop : coops) {
            System.out.println("Coop ID: " + coop.getId() + " - " + coop.getDescription());
        }
        
        int coopId = 0;
        while (true) {
            try {
                System.out.print("Enter Coop ID: ");
                coopId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Coop ID.");
                scanner.nextLine();
            }
        }
        
        ChickenCoop selectedCoop = findCoopById(coopId);
        if (selectedCoop == null) {
            System.out.println("Coop not found!");
            return;
        }
        
        System.out.println("\n--- Enter chicken data ---");
        
        int id = 0;
        while (true) {
            try {
                System.out.print("Chicken ID: ");
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Chicken ID.");
                scanner.nextLine();
            }
        }
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Color: ");
        String color = scanner.nextLine();
        
        int age = 0;
        while (true) {
            try {
                System.out.print("Age: ");
                age = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Age.");
                scanner.nextLine();
            }
        }
        
        boolean isMolting = false;
        while (true) {
            try {
                System.out.print("Is molting? (1 for true / 0 for false): ");
                int moltingInput = scanner.nextInt();
                scanner.nextLine();
                
                if (moltingInput == 1) {
                    isMolting = true;
                    break;
                } else if (moltingInput == 0) {
                    isMolting = false;
                    break;
                } else {
                    System.out.println("Error: Only 1 (true) or 0 (false) are allowed for molting status.");
                }
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed. Please enter 1 for true or 0 for false.");
                scanner.nextLine();
            }
        }
        
        Chicken chicken = new Chicken(id, name, color, age, isMolting);
        selectedCoop.addChicken(chicken);
        System.out.println("----Chicken added successfully------");
    }
    
    private static void listAllCoops() {
        System.out.println("\n--- ALL COOPS ---");
        if (coops.isEmpty()) {
            System.out.println("No coops available.");
            return;
        }
        
        for (ChickenCoop coop : coops) {
            System.out.println(coop);
        }
    }
    
    private static void listChickensInCoop() {
        if (coops.isEmpty()) {
            System.out.println("No coops available.");
            return;
        }
        
        int coopId = 0;
        while (true) {
            try {
                System.out.print("Enter Coop ID to list chickens: ");
                coopId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Coop ID.");
                scanner.nextLine();
            }
        }
        
        ChickenCoop selectedCoop = findCoopById(coopId);
        if (selectedCoop != null) {
            selectedCoop.listChickens();
        } else {
            System.out.println("Coop not found!");
        }
    }
    
    private static void removeChickenFromCoop() {
        if (coops.isEmpty()) {
            System.out.println("No coops available.");
            return;
        }
        
        int coopId = 0;
        while (true) {
            try {
                System.out.print("Enter Coop ID: ");
                coopId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Coop ID.");
                scanner.nextLine();
            }
        }
        
        ChickenCoop selectedCoop = findCoopById(coopId);
        if (selectedCoop == null) {
            System.out.println("Coop not found!");
            return;
        }
        
        int chickenId = 0;
        while (true) {
            try {
                System.out.print("Enter Chicken ID to remove: ");
                chickenId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Chicken ID.");
                scanner.nextLine();
            }
        }
        
        selectedCoop.removeChicken(chickenId);
    }
    
    private static void makeChickensDoStuff() {
        if (coops.isEmpty()) {
            System.out.println("No coops available.");
            return;
        }
        
        System.out.println("\n--- MAKE CHICKENS DO STUFF ---");
        System.out.println("1. Make specific chicken do stuff");
        System.out.println("2. Make all chickens in coop do stuff");
        
        int option = 0;
        while (true) {
            try {
                System.out.print("Select option: ");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed.");
                scanner.nextLine();
            }
        }
        
        switch (option) {
            case 1 -> makeSpecificChickenDoStuff();
            case 2 -> makeAllChickensInCoopDoStuff();
            default -> System.out.println("Invalid option! Please select 1 or 2.");
        }
    }
    
    private static void makeSpecificChickenDoStuff() {
        int coopId = 0;
        while (true) {
            try {
                System.out.print("Enter Coop ID: ");
                coopId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Coop ID.");
                scanner.nextLine();
            }
        }
        
        int chickenId = 0;
        while (true) {
            try {
                System.out.print("Enter Chicken ID: ");
                chickenId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Chicken ID.");
                scanner.nextLine();
            }
        }
        
        ChickenCoop selectedCoop = findCoopById(coopId);
        if (selectedCoop != null) {
            Chicken chicken = selectedCoop.findChickenById(chickenId);
            if (chicken != null) {
                System.out.println("\n--- " + chicken.getName() + " IS DOING STUFF ---");
                chicken.doStuff();
            } else {
                System.out.println("Chicken not found!");
            }
        } else {
            System.out.println("Coop not found!");
        }
    }
    
    private static void makeAllChickensInCoopDoStuff() {
        int coopId = 0;
        while (true) {
            try {
                System.out.print("Enter Coop ID: ");
                coopId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Coop ID.");
                scanner.nextLine();
            }
        }
        
        ChickenCoop selectedCoop = findCoopById(coopId);
        if (selectedCoop != null) {
            selectedCoop.makeAllDoStuff();
        } else {
            System.out.println("Coop not found!");
        }
    }
    
    private static void removeCoop() {
        if (coops.isEmpty()) {
            System.out.println("No coops available.");
            return;
        }
        
        int coopId = 0;
        while (true) {
            try {
                System.out.print("Enter Coop ID to remove: ");
                coopId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Coop ID.");
                scanner.nextLine();
            }
        }
        
        ChickenCoop coopToRemove = findCoopById(coopId);
        if (coopToRemove != null) {
            coops.remove(coopToRemove);
            System.out.println("Coop removed successfully!");
        } else {
            System.out.println("Coop not found!");
        }
    }
    
    private static void updateChickenData() {
        if (coops.isEmpty()) {
            System.out.println("No coops available. Please create a coop and add chickens first.");
            return;
        }
        
        boolean hasChickens = false;
        for (ChickenCoop coop : coops) {
            if (coop.getChickenCount() > 0) {
                hasChickens = true;
                break;
            }
        }
        
        if (!hasChickens) {
            System.out.println("No chickens available. Please add chickens first.");
            return;
        }
        
        System.out.println("\n--- UPDATE CHICKEN DATA ---");
        
        System.out.println("Available chickens:");
        for (ChickenCoop coop : coops) {
            if (coop.getChickenCount() > 0) {
                System.out.println("Coop " + coop.getId() + " - " + coop.getDescription() + ":");
                coop.listChickens();
            }
        }
        
        int chickenId = 0;
        while (true) {
            try {
                System.out.print("Enter Chicken ID to update: ");
                chickenId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Chicken ID.");
                scanner.nextLine();
            }
        }
        
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        
        System.out.print("Enter new color: ");
        String newColor = scanner.nextLine();
        
        int newAge = 0;
        while (true) {
            try {
                System.out.print("Enter new age: ");
                newAge = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Age.");
                scanner.nextLine();
            }
        }
        
        boolean newMoltingStatus = false;
        while (true) {
            try {
                System.out.print("Is molting? (1 for true / 0 for false): ");
                int moltingInput = scanner.nextInt();
                scanner.nextLine();
                
                if (moltingInput == 1) {
                    newMoltingStatus = true;
                    break;
                } else if (moltingInput == 0) {
                    newMoltingStatus = false;
                    break;
                } else {
                    System.out.println("Error: Only 1 (true) or 0 (false) are allowed for molting status.");
                }
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed. Please enter 1 for true or 0 for false.");
                scanner.nextLine();
            }
        }
        
        JSONFileManager.updateChickenData(coops, chickenId, newName, newColor, newAge, newMoltingStatus);
    }
    
    private static void deleteChickenFromJSON() {
        if (coops.isEmpty()) {
            System.out.println("No coops available. Please create a coop and add chickens first.");
            return;
        }
        
        boolean hasChickens = false;
        for (ChickenCoop coop : coops) {
            if (coop.getChickenCount() > 0) {
                hasChickens = true;
                break;
            }
        }
        
        if (!hasChickens) {
            System.out.println("No chickens available. Please add chickens first.");
            return;
        }
        
        System.out.println("\n--- DELETE CHICKEN FROM JSON ---");
        
        System.out.println("Available chickens:");
        for (ChickenCoop coop : coops) {
            if (coop.getChickenCount() > 0) {
                System.out.println("Coop " + coop.getId() + " - " + coop.getDescription() + ":");
                coop.listChickens();
            }
        }
        
        int coopId = 0;
        while (true) {
            try {
                System.out.print("Enter Coop ID: ");
                coopId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Coop ID.");
                scanner.nextLine();
            }
        }
        
        int chickenId = 0;
        while (true) {
            try {
                System.out.print("Enter Chicken ID to delete: ");
                chickenId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Error: Only numbers are allowed for Chicken ID.");
                scanner.nextLine();
            }
        }
        
        JSONFileManager.deleteChickenFromFile(coops, coopId, chickenId);
    }
    
    private static ChickenCoop findCoopById(int coopId) {
        for (ChickenCoop coop : coops) {
            if (coop.getId() == coopId) {
                return coop;
            }
        }
        return null;
    }
}