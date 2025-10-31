package ec.espe.edu.chickenFarmSimulator.controller;
import ec.espe.edu.chickenFarmSimulator.model.Chicken;
import ec.espe.edu.chickenFarmSimulator.model.ChickenCoop;
import ec.espe.edu.chickenFarmSimulator.model.ChickenFarmer;
import ec.espe.edu.chickenFarmSimulator.view.ChickenFarmView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
/**
 *
 * @author Mathews Pastor
 */
public class ChickenFarmController {
    private ChickenFarmer farmer;
    private ChickenFarmView view;
    private Scanner scanner;
    private int nextChickenId = 1;

    public ChickenFarmController(ChickenFarmer farmer, ChickenFarmView view) {
        this.farmer = farmer;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }
    
    private void saveData() {
        // Usamos GsonBuilder para que el JSON se vea bonito (pretty printing)
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filename = "farm_data.json";

        // Convertimos el objeto 'farmer' a un string JSON
        String jsonData = gson.toJson(farmer);

        // Escribimos el string al archivo
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(jsonData);
            view.displayMessage("Data saved successfully to " + filename);
        } catch (IOException e) {
            view.displayMessage("Error: Could not save data to file.");
            e.printStackTrace();
        }
    }

    public void loadData() {
        Gson gson = new Gson();
        String filename = "farm_data.json";

        try (Reader reader = new FileReader(filename)) {
            // Lee el archivo JSON y lo convierte de nuevo en un objeto ChickenFarmer
            ChickenFarmer loadedFarmer = gson.fromJson(reader, ChickenFarmer.class);
            
            if (loadedFarmer != null) {
                this.farmer = loadedFarmer; 
                view.displayMessage("Data loaded successfully from " + filename);
                
                // Sincroniza el ID del próximo pollo
                int maxId = 0;
                for (ChickenCoop coop : farmer.getCoops()) {
                    for (Chicken chicken : coop.getChickens()) {
                        if (chicken.getId() > maxId) {
                            maxId = chicken.getId();
                        }
                    }
                }
                this.nextChickenId = maxId + 1; 
            } else {
                view.displayMessage("No data found in " + filename + ". Starting fresh.");
            }

        } catch (IOException e) {
            view.displayMessage("Note: No 'farm_data.json' file found. A new one will be created when you save.");
        } catch (Exception e) {
            view.displayMessage("Error: Could not load data from file. File might be corrupt.");
            e.printStackTrace();
        }
    }
    
    public void run() {
        boolean running = true;
        while (running) {
            view.showMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    handleCoopManagement();
                    break;
                case "2":
                    simulateDay();
                    break;
                case "3":
                    handleFarmerManagement();
                    break;
                case "4":
                    saveData();
                    break;
                case "5":
                    running = false;
                    view.displayMessage("Exiting simulator. Goodbye!");
                    break;
                default:
                    view.displayMessage("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
    
    
    private void handleCoopManagement() {
        boolean inCoopMenu = true;
        while (inCoopMenu) {
            view.showCoopManagementMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    createCoop();
                    break;
                case "2":
                    readCoops();
                    break;
                case "3":
                    editCoop();
                    break;
                case "4":
                    deleteCoop();
                    break;
                case "5":
                    manageChickensInCoop();
                    break;
                case "6":
                    inCoopMenu = false;
                    break;
                default:
                    view.displayMessage("Invalid option. Please try again.");
            }
        }
    }

    private void handleFarmerManagement() {
        boolean inFarmerMenu = true;
        while (inFarmerMenu) {
            view.showFarmerManagementMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    view.displayFarmerInfo(farmer);
                    break;
                case "2":
                    editFarmerName();
                    break;
                case "3":
                    inFarmerMenu = false;
                    break;
                default:
                    view.displayMessage("Invalid option. Please try again.");
            }
        }
    }

    private void handleChickenManagement(ChickenCoop coop) {
        boolean inChickenMenu = true;
        while (inChickenMenu) {
            view.showChickenManagementMenu(coop.getChickenCoopNumber());
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    createChickens(coop);
                    break;
                case "2":
                    view.displayChickens(coop.getChickenCoopNumber(), coop.getChickens(), coop.getCapacity());
                    break;
                case "3":
                    editChicken(coop);
                    break;
                case "4":
                    deleteChicken(coop);
                    break;
                case "5":
                    inChickenMenu = false;
                    break;
                default:
                    view.displayMessage("Invalid option. Please try again.");
            }
        }
    }

    private void createCoop() {
        try {
            view.displayMessage("Enter maximum capacity for the new coop:");
            int capacity = scanner.nextInt();
            scanner.nextLine(); 
            if (capacity <= 0) {
                view.displayMessage("Capacity must be positive.");
                return;
            }
            ChickenCoop newCoop = farmer.addCoop(capacity);
            view.displayMessage("Coop created successfully. ID: " + newCoop.getChickenCoopNumber());
        } catch (InputMismatchException e) {
            view.displayMessage("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private void readCoops() {
        view.displayCoops(farmer.getCoops());
    }

    private void editCoop() {
        try {
            readCoops();
            view.displayMessage("Enter the Coop ID to edit:");
            int coopId = scanner.nextInt();
            scanner.nextLine(); 

            if (farmer.findCoop(coopId).isPresent()) {
                view.displayMessage("Enter the new capacity for the Coop:");
                int newCapacity = scanner.nextInt();
                scanner.nextLine(); 
                if (newCapacity <= 0) {
                    view.displayMessage("Capacity must be positive.");
                    return;
                }
                farmer.updateCoop(coopId, newCapacity);
                view.displayMessage("Coop updated.");
            } else {
                view.displayMessage("Coop not found.");
            }
        } catch (InputMismatchException e) {
            view.displayMessage("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private void deleteCoop() {
        try {
            readCoops();
            view.displayMessage("Enter the Coop ID to delete:");
            int coopId = scanner.nextInt();
            scanner.nextLine();

            if (farmer.removeCoop(coopId)) {
                view.displayMessage("Coop deleted successfully.");
            } else {
                view.displayMessage("Coop not found.");
            }
        } catch (InputMismatchException e) {
            view.displayMessage("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }
    
    private void manageChickensInCoop() {
        try {
            readCoops();
            view.displayMessage("Enter the Coop ID to manage its chickens:");
            int coopId = scanner.nextInt();
            scanner.nextLine(); 

            Optional<ChickenCoop> coopOpt = farmer.findCoop(coopId);
            if (coopOpt.isPresent()) {
                handleChickenManagement(coopOpt.get());
            } else {
                view.displayMessage("Coop not found.");
            }
        } catch (InputMismatchException e) {
            view.displayMessage("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private void simulateDay() {
        view.displayMessage("\n=== SIMULATING A DAY ON THE FARM ===");
        if(farmer.getCoops().isEmpty()) {
            view.displayMessage("No coops to simulate.");
            return;
        }
        int totalEggsToday = 0;
        for (ChickenCoop coop : farmer.getCoops()) {
            totalEggsToday += coop.simulateCoopDay();
        }
        view.displayMessage("\n=== END OF DAY SUMMARY ===");
        view.displayMessage("Total eggs laid today on the farm: " + totalEggsToday);
        view.displayCoops(farmer.getCoops());
    }

    private void editFarmerName() {
        view.displayMessage("Enter new Farmer Name (Current: " + farmer.getName() + "):");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            farmer.setName(newName);
            view.displayMessage("Farmer name updated to: " + farmer.getName());
        } else {
            view.displayMessage("Name cannot be empty. Update cancelled.");
        }
    }

    private void createChickens(ChickenCoop coop) {
        try {
            int availableSpace = coop.getCapacity() - coop.getChickens().size();
            if (availableSpace <= 0) {
                view.displayMessage("Coop is full. Cannot add more chickens.");
                return;
            }

            view.displayMessage("How many chickens to add to Coop " + coop.getChickenCoopNumber() + "? (Max: " + availableSpace + "):");
            int numToAdd = scanner.nextInt();
            scanner.nextLine(); 

            if (numToAdd <= 0) {
                view.displayMessage("Invalid number.");
                return;
            }
            if (numToAdd > availableSpace) {
                view.displayMessage("Not enough space. Capping at " + availableSpace + " chickens.");
                numToAdd = availableSpace;
            }

            for (int i = 0; i < numToAdd; i++) {
                view.displayMessage("\n--- Details for Chicken " + (i + 1) + " of " + numToAdd + " (ID: " + nextChickenId + ") ---");
                System.out.print("Name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Color: ");
                String color = scanner.nextLine().trim();
                System.out.print("Age (years): ");
                int age = scanner.nextInt();
                scanner.nextLine(); 
                System.out.print("Is it molting (y/n)?: ");
                boolean isMolting = scanner.nextLine().trim().equalsIgnoreCase("y");

                Chicken chicken = new Chicken(nextChickenId++, name, color, age, isMolting);
                coop.addChicken(chicken);
            }
            view.displayMessage(numToAdd + " chickens added to Coop " + coop.getChickenCoopNumber() + ".");

        } catch (InputMismatchException e) {
            view.displayMessage("Invalid input. Please enter a number for age.");
            scanner.nextLine(); 
        }
    }

    private void editChicken(ChickenCoop coop) {
        try {
            view.displayChickens(coop.getChickenCoopNumber(), coop.getChickens(), coop.getCapacity());
            view.displayMessage("Enter the ID of the chicken to edit:");
            int chickenId = scanner.nextInt();
            scanner.nextLine(); 

            Optional<Chicken> chickenOpt = coop.findChicken(chickenId);
            if (chickenOpt.isPresent()) {
                Chicken chicken = chickenOpt.get();
                view.displayMessage("\nEditing Chicken " + chicken.getName() + " (ID: " + chicken.getId() + "). Leave blank to keep current value.");

                System.out.print("New Name (Current: " + chicken.getName() + "): ");
                String name = scanner.nextLine().trim();
                if (!name.isEmpty()) chicken.setName(name);

                System.out.print("New Color (Current: " + chicken.getColor() + "): ");
                String color = scanner.nextLine().trim();
                if (!color.isEmpty()) chicken.setColor(color);

                System.out.print("New Age (Current: " + chicken.getAge() + "): ");
                String ageStr = scanner.nextLine().trim();
                if (!ageStr.isEmpty()) chicken.setAge(Integer.parseInt(ageStr));

                System.out.print("Is it Molting? (y/n) (Current: " + (chicken.getIsMolting() ? "Yes" : "No") + "): ");
                String moltingStr = scanner.nextLine().trim();
                if (!moltingStr.isEmpty()) chicken.setIsMolting(moltingStr.equalsIgnoreCase("y"));

                view.displayMessage("Chicken updated.");
            } else {
                view.displayMessage("Chicken not found.");
            }
        } catch (InputMismatchException | NumberFormatException e) {
            view.displayMessage("Invalid input. Please enter a valid number.");
            scanner.nextLine(); 
        }
    }

    private void deleteChicken(ChickenCoop coop) {
        try {
            view.displayChickens(coop.getChickenCoopNumber(), coop.getChickens(), coop.getCapacity());
            view.displayMessage("Enter the ID of the chicken to delete:");
            int chickenId = scanner.nextInt();
            scanner.nextLine();

            if (coop.removeChicken(chickenId)) {
                view.displayMessage("Chicken deleted successfully.");
            } else {
                view.displayMessage("Chicken not found.");
            }
        } catch (InputMismatchException e) {
            view.displayMessage("Invalid input. Please enter a number.");
            scanner.nextLine(); 
        }
    }
}
