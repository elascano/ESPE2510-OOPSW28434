/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.ChickenFarmSimulator.view;

/**
 *
 * @author Paulo Ramos
 */
import java.io.*;
import java.util.*;
import com.google.gson.*;
import ec.edu.espe.ChickenFarmSimulator.model.Chicken;
import ec.edu.espe.ChickenFarmSimulator.model.ChickenCoop;

public class ChickenFarmSimulator {

    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = DATA_DIR + File.separator + "chicken_farm.json";
    private static List<ChickenCoop> coops = Arrays.asList(new ChickenCoop(1), new ChickenCoop(2));
    private static Scanner scanner = new Scanner(System.in);
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) dir.mkdir();

        int option = 0;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Insert chicken");
            System.out.println("2. List chickens");
            System.out.println("3. Delete chicken");
            System.out.println("4. Update chicken");
            System.out.println("5. Find chicken");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (option) {
                case 1 -> insertChicken();
                case 2 -> listChickens();
                case 3 -> deleteChicken();
                case 4 -> updateChicken();
                case 5 -> findChicken();
                case 6 -> System.out.println("Exiting simulator...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 6);
    }

    private static void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (Reader reader = new FileReader(file)) {
            JsonObject jsonData = gson.fromJson(reader, JsonObject.class);
            coops = Arrays.asList(new ChickenCoop(1), new ChickenCoop(2));

            JsonArray coopsJson = jsonData.getAsJsonArray("coops");
            for (JsonElement coopEl : coopsJson) {
                JsonObject coopObj = coopEl.getAsJsonObject();
                int coopId = coopObj.get("id").getAsInt();
                ChickenCoop coop = coops.stream().filter(c -> c.getId() == coopId).findFirst().orElse(null);
                if (coop != null) {
                    JsonArray chickensJson = coopObj.getAsJsonArray("chickens");
                    for (JsonElement chEl : chickensJson) {
                        JsonObject chObj = chEl.getAsJsonObject();
                        Chicken ch = new Chicken(
                                chObj.get("id").getAsInt(),
                                chObj.get("name").getAsString(),
                                chObj.get("color").getAsString(),
                                chObj.get("age").getAsInt(),
                                chObj.get("molting").getAsBoolean()
                        );
                        coop.addChicken(ch);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveData() {
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> coopsList = new ArrayList<>();
        for (ChickenCoop coop : coops) {
            Map<String, Object> coopMap = new HashMap<>();
            coopMap.put("id", coop.getId());
            List<Map<String, Object>> chickensList = new ArrayList<>();
            for (Chicken ch : coop.getChickens()) {
                Map<String, Object> chMap = new HashMap<>();
                chMap.put("id", ch.getId());
                chMap.put("name", ch.getName());
                chMap.put("color", ch.getColor());
                chMap.put("age", ch.getAge());
                chMap.put("molting", ch.isMolting());
                chickensList.add(chMap);
            }
            coopMap.put("chickens", chickensList);
            coopsList.add(coopMap);
        }
        data.put("coops", coopsList);

        try (Writer writer = new FileWriter(DATA_FILE)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertChicken() {
        loadData();
        System.out.println("Available coops: 1 and 2");
        System.out.print("Enter coop ID (1 or 2): ");
        int coopId = scanner.nextInt();
        scanner.nextLine();

        ChickenCoop coop = coops.stream().filter(c -> c.getId() == coopId).findFirst().orElse(null);
        if (coop == null) {
            System.out.println("Invalid coop ID.");
            return;
        }

        int nextId = coop.getChickens().isEmpty() ? 1 :
                coop.getChickens().stream().mapToInt(Chicken::getId).max().getAsInt() + 1;

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Is molting? (y/n): ");
        boolean molting = scanner.nextLine().equalsIgnoreCase("y");

        Chicken ch = new Chicken(nextId, name, color, age, molting);
        coop.addChicken(ch);
        saveData();
        System.out.println("Chicken '" + name + "' added to coop " + coop.getId() + " with ID " + nextId);
    }

    private static void listChickens() {
        loadData();
        for (ChickenCoop coop : coops) {
            System.out.println("\nCoop " + coop.getId() + ":");
            if (coop.getChickens().isEmpty()) {
                System.out.println("\tNo chickens.");
                continue;
            }
            System.out.println("\nID\tName\tColor\tAge\tMolting");
            System.out.println("---------------------------------------------------");
            for (Chicken ch : coop.getChickens()) {
                System.out.printf("%d\t%s\t%s\t%d\t%s%n",
                        ch.getId(), ch.getName(), ch.getColor(), ch.getAge(), ch.isMolting() ? "Yes" : "No");
            }
            System.out.println("---------------------------------------------------\n");
        }
    }

    private static void deleteChicken() {
        loadData();
        System.out.print("Enter coop ID (1 or 2): ");
        int coopId = scanner.nextInt();
        scanner.nextLine();
        ChickenCoop coop = coops.stream().filter(c -> c.getId() == coopId).findFirst().orElse(null);

        if (coop == null || coop.getChickens().isEmpty()) {
            System.out.println("No chickens in this coop.");
            return;
        }

        System.out.print("Enter chicken ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<Chicken> chOpt = coop.getChickens().stream().filter(c -> c.getId() == id).findFirst();
        if (chOpt.isPresent()) {
            coop.getChickens().remove(chOpt.get());
            saveData();
            System.out.println("🗑️ Chicken " + id + " deleted.");
        } else {
            System.out.println("Chicken not found.");
        }
    }

    private static void updateChicken() {
        loadData();
        System.out.print("Enter coop ID (1 or 2): ");
        int coopId = scanner.nextInt();
        scanner.nextLine();
        ChickenCoop coop = coops.stream().filter(c -> c.getId() == coopId).findFirst().orElse(null);

        if (coop == null || coop.getChickens().isEmpty()) {
            System.out.println("No chickens found.");
            return;
        }

        System.out.print("Enter chicken ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Chicken ch = coop.getChickens().stream().filter(c -> c.getId() == id).findFirst().orElse(null);

        if (ch != null) {
            System.out.print("New name (" + ch.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) ch.setName(name);

            System.out.print("New color (" + ch.getColor() + "): ");
            String color = scanner.nextLine();
            if (!color.isEmpty()) ch.setColor(color);

            System.out.print("New age (" + ch.getAge() + "): ");
            String ageInput = scanner.nextLine();
            if (!ageInput.isEmpty()) ch.setAge(Integer.parseInt(ageInput));

            System.out.print("Is molting? (y/n): ");
            String moltingInput = scanner.nextLine();
            if (!moltingInput.isEmpty()) ch.setMolting(moltingInput.equalsIgnoreCase("y"));

            saveData();
            System.out.println("Chicken updated successfully.");
        } else {
            System.out.println("Chicken not found.");
        }
    }

    private static void findChicken() {
        loadData();
        System.out.print("Enter chicken ID to find: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;

        for (ChickenCoop coop : coops) {
            Chicken ch = coop.getChickens().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
            if (ch != null) {
                System.out.println("\nFound in coop " + coop.getId() + ":");
                System.out.println("\nID\tName\tColor\tAge\tMolting");
                System.out.println("---------------------------------------------------");
                System.out.printf("%d\t%s\t%s\t%d\t%s%n",
                        ch.getId(), ch.getName(), ch.getColor(), ch.getAge(), ch.isMolting() ? "Yes" : "No");
                System.out.println("---------------------------------------------------\n");
                found = true;
            }
        }

        if (!found) System.out.println("Chicken not found.");
    }
}