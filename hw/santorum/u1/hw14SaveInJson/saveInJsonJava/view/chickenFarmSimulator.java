package view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.chicken;
import model.coop;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class chickenFarmSimulator {
    private static final String DATA_FILE = "data.json";
    private static ArrayList<coop> coops = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        loadData();
        mainMenu();
    }

    private static void saveData() {
        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            gson.toJson(coops, writer);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void loadData() {
        try {
            File file = new File(DATA_FILE);
            if (file.exists()) {
                Type type = new TypeToken<ArrayList<coop>>() {}.getType();
                coops = gson.fromJson(new FileReader(file), type);
                if (coops == null) coops = new ArrayList<>();
            }
        } catch (Exception e) {
            coops = new ArrayList<>();
        }
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("=== Chicken Farm Simulator ===");
            System.out.println("1. Add coop");
            System.out.println("2. Add chicken");
            System.out.println("3. View chickens");
            System.out.println("4. Edit chicken");
            System.out.println("5. Delete chicken");
            System.out.println("6. Find chicken");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1": addCoop(); break;
                case "2": addChicken(); break;
                case "3": viewChickens(); break;
                case "4": editChicken(); break;
                case "5": deleteChicken(); break;
                case "6": findChicken(); break;
                case "0":
                    saveData();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.\n");
            }
        }
    }

    private static void addCoop() {
        System.out.print("Enter coop ID: ");
        String id = scanner.nextLine();

        for (coop c : coops) {
            if (c.getId().equals(id)) {
                System.out.println("A coop with that ID already exists.\n");
                return;
            }
        }

        coops.add(new coop(id));
        saveData();
        System.out.println("Coop added!\n");
    }

    private static void addChicken() {
        if (coops.isEmpty()) {
            System.out.println("You must create a coop first.\n");
            return;
        }

        System.out.println("Available coops:");
        for (coop c : coops) System.out.println("- Coop " + c.getId());
        System.out.print("Enter coop ID: ");
        String coopId = scanner.nextLine();

        coop coopObj = coops.stream().filter(c -> c.getId().equals(coopId)).findFirst().orElse(null);
        if (coopObj == null) {
            System.out.println("Coop not found.\n");
            return;
        }

        System.out.print("Enter chicken ID: ");
        String id = scanner.nextLine();
        if (coopObj.findChickenById(id) != null) {
            System.out.println("A chicken with that ID already exists in this coop.\n");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Is molting? (y/n): ");
        boolean molting = scanner.nextLine().trim().equalsIgnoreCase("y");

        coopObj.addChicken(new chicken(id, name, color, age, molting));
        saveData();
        System.out.println("Chicken added!\n");
    }

    private static void viewChickens() {
        if (coops.isEmpty()) {
            System.out.println("No coops available.\n");
            return;
        }

        for (coop coopObj : coops) {
            System.out.println("\n=== Coop " + coopObj.getId() + " ===");
            System.out.println("ID    | Name       | Color    | Age | Molting");
            System.out.println("------+------------+----------+-----+--------");
            if (coopObj.getChickens().isEmpty()) {
                System.out.println("No chickens.");
            } else {
                for (chicken c : coopObj.getChickens()) {
                    System.out.println(c);
                }
            }
        }
        System.out.println();
    }

    private static void editChicken() {
        System.out.print("Enter coop ID: ");
        String coopId = scanner.nextLine();

        coop coopObj = coops.stream().filter(c -> c.getId().equals(coopId)).findFirst().orElse(null);
        if (coopObj == null) {
            System.out.println("Coop not found.\n");
            return;
        }

        System.out.print("Enter chicken ID to edit: ");
        String id = scanner.nextLine();
        chicken chickenObj = coopObj.findChickenById(id);

        if (chickenObj == null) {
            System.out.println("Chicken not found.\n");
            return;
        }

        System.out.print("Name (" + chickenObj.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) chickenObj.setName(name);

        System.out.print("Color (" + chickenObj.getColor() + "): ");
        String color = scanner.nextLine();
        if (!color.isEmpty()) chickenObj.setColor(color);

        System.out.print("Age (" + chickenObj.getAge() + "): ");
        String ageStr = scanner.nextLine();
        if (!ageStr.isEmpty()) chickenObj.setAge(Integer.parseInt(ageStr));

        System.out.print("Molting (y/n): ");
        String moltingStr = scanner.nextLine();
        if (!moltingStr.isEmpty()) chickenObj.setMolting(moltingStr.equalsIgnoreCase("y"));

        saveData();
        System.out.println("Chicken updated!\n");
    }

    private static void deleteChicken() {
        System.out.print("Enter coop ID: ");
        String coopId = scanner.nextLine();

        coop coopObj = coops.stream().filter(c -> c.getId().equals(coopId)).findFirst().orElse(null);
        if (coopObj == null) {
            System.out.println("Coop not found.\n");
            return;
        }

        System.out.print("Enter chicken ID to delete: ");
        String id = scanner.nextLine();

        if (coopObj.removeChickenById(id)) {
            saveData();
            System.out.println("Chicken deleted.\n");
        } else {
            System.out.println("Chicken not found.\n");
        }
    }

    private static void findChicken() {
        System.out.print("Enter chicken ID to find: ");
        String id = scanner.nextLine();

        boolean found = false;
        for (coop coopObj : coops) {
            List<chicken> matches = coopObj.getChickens().stream()
                    .filter(c -> c.getId().equals(id)).toList();
            if (!matches.isEmpty()) {
                System.out.println("\nFound in Coop " + coopObj.getId() + ":");
                System.out.println("ID    | Name       | Color    | Age | Molting");
                System.out.println("------+------------+----------+-----+--------");
                for (chicken c : matches) System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Chicken not found.\n");
        }
    }
}
