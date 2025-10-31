package ec.espe.edu.chickenFarmSimulator.view;
import ec.espe.edu.chickenFarmSimulator.model.Chicken;
import ec.espe.edu.chickenFarmSimulator.model.ChickenCoop;
import ec.espe.edu.chickenFarmSimulator.model.ChickenFarmer;
import java.util.ArrayList;
/**
 *
 * @author Mathews Pastor
 */
public class ChickenFarmView {

    public void showMainMenu() {
        System.out.println("\n======================================");
        System.out.println("     FARM SIMULATOR MENU");
        System.out.println("======================================");
        System.out.println("1. Manage Farm (Coops)");
        System.out.println("2. Simulate Daily Actions");
        System.out.println("3. Manage Current Farmer");
        System.out.println("4. Save Data ");
        System.out.println("5. Exit");
        System.out.println("======================================");
        System.out.print("Select an option: ");
    }

    public void showCoopManagementMenu() {
        System.out.println("\n======================================");
        System.out.println("     CHICKEN COOP MANAGEMENT");
        System.out.println("======================================");
        System.out.println("1. Create Coop");
        System.out.println("2. Read/Show Coops");
        System.out.println("3. Edit Coop");
        System.out.println("4. Delete Coop");
        System.out.println("5. Manage Chickens in a Coop");
        System.out.println("6. Back to Main Menu");
        System.out.println("======================================");
        System.out.print("Select an option: ");
    }

    public void showFarmerManagementMenu() {
        System.out.println("\n======================================");
        System.out.println("     FARMER MANAGEMENT");
        System.out.println("======================================");
        System.out.println("1. View Current Farmer Info");
        System.out.println("2. Edit Farmer Name");
        System.out.println("3. Back to Main Menu");
        System.out.println("======================================");
        System.out.print("Select an option: ");
    }

    public void showChickenManagementMenu(int coopId) {
        System.out.println("\n======================================");
        System.out.printf("  CHICKEN MANAGEMENT - COOP ID %d%n", coopId);
        System.out.println("======================================");
        System.out.println("1. Create Chicken(s)");
        System.out.println("2. Read/Show Chickens");
        System.out.println("3. Edit Chicken");
        System.out.println("4. Delete Chicken");
        System.out.println("5. Back to Coop Menu");
        System.out.println("======================================");
        System.out.print("Select an option: ");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public void displayCoops(ArrayList<ChickenCoop> coops) {
        System.out.println("\n--- FARMER'S CHICKEN COOPS ---");
        if (coops.isEmpty()) {
            System.out.println("No coops found.");
        } else {
            coops.forEach(System.out::println);
        }
        System.out.println("-------------------------------");
    }

    public void displayChickens(int coopId, ArrayList<Chicken> chickens, int capacity) {
        System.out.printf("%n--- CHICKENS IN COOP %d (%d/%d) ---%n", coopId, chickens.size(), capacity);
        if (chickens.isEmpty()) {
            System.out.println("No chickens in this coop.");
        } else {
            chickens.forEach(System.out::println);
        }
        System.out.println("----------------------------------------------------");
    }

    public void displayFarmerInfo(ChickenFarmer farmer) {
        System.out.println("\n======================================");
        System.out.printf("  FARMER INFORMATION: %s%n", farmer.getName());
        System.out.println("======================================");
        System.out.println("ID: " + farmer.getId());
        System.out.println("Name: " + farmer.getName());
        System.out.println("Total Coops: " + farmer.getCoops().size());
        System.out.println("======================================");
    }
}

