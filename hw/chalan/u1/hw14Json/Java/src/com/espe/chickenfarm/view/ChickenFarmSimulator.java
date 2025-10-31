package com.espe.chickenfarm.view;

import com.espe.chickenfarm.model.Farmer;
import com.espe.chickenfarm.model.Chicken;
import com.espe.chickenfarm.model.ChickenCoop;

import java.util.*;
import java.io.*;

public class ChickenFarmSimulator {
    private List<Farmer> farmers;
    private List<ChickenCoop> coops;
    private Farmer currentFarmer;
    private Scanner scanner;
    private static final String DATA_DIR = "farmChickenData";

    public ChickenFarmSimulator() {
        this.farmers = new ArrayList<>();
        this.coops = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        
        // Crear directorio de datos si no existe
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        
        loadData();
    }

    private void loadData() {
        try {
            // Cargar granjeros
            File farmersFile = new File(DATA_DIR + "/farmers.json");
            if (farmersFile.exists()) {
                String farmersJson = readFile(farmersFile);
                if (!farmersJson.trim().isEmpty()) {
                    farmers = parseFarmersJson(farmersJson);
                }
            }

            // Cargar gallineros
            File coopsFile = new File(DATA_DIR + "/coops.json");
            if (coopsFile.exists()) {
                String coopsJson = readFile(coopsFile);
                if (!coopsJson.trim().isEmpty()) {
                    coops = parseCoopsJson(coopsJson);
                }
            }

            System.out.println("Loaded " + farmers.size() + " farmers and " + coops.size() + " coops from " + DATA_DIR);
        
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            farmers = new ArrayList<>();
            coops = new ArrayList<>();
        }
    }

    private String readFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    private List<Farmer> parseFarmersJson(String json) {
        List<Farmer> farmerList = new ArrayList<>();
        if (!json.contains("\"id\"")) return farmerList;
        
        try {
            // Parseo simple de JSON
            String[] farmersArray = json.split("\\},\\s*\\{");
            for (String farmerStr : farmersArray) {
                farmerStr = farmerStr.replaceAll("[\\[\\]{}]", "").trim();
                if (farmerStr.isEmpty()) continue;
                
                int id = extractIntValue(farmerStr, "id");
                String name = extractStringValue(farmerStr, "name");
                
                if (id != -1 && name != null) {
                    Farmer farmer = new Farmer(id, name);
                    
                    // Parsear coopIds
                    String coopIdsStr = extractArrayValue(farmerStr, "coopIds");
                    if (coopIdsStr != null) {
                        String[] coopIds = coopIdsStr.split(",");
                        for (String coopId : coopIds) {
                            try {
                                farmer.addCoop(Integer.parseInt(coopId.trim()));
                            } catch (NumberFormatException e) {
                                // Ignorar números inválidos
                            }
                        }
                    }
                    farmerList.add(farmer);
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing farmers JSON: " + e.getMessage());
        }
        return farmerList;
    }

    private List<ChickenCoop> parseCoopsJson(String json) {
        List<ChickenCoop> coopList = new ArrayList<>();
        if (!json.contains("\"id\"")) return coopList;
        
        try {
            String[] coopsArray = json.split("\\},\\s*\\{");
            for (String coopStr : coopsArray) {
                coopStr = coopStr.replaceAll("[\\[\\]{}]", "").trim();
                if (coopStr.isEmpty()) continue;
                
                int id = extractIntValue(coopStr, "id");
                int farmerId = extractIntValue(coopStr, "farmerId");
                
                if (id != -1 && farmerId != -1) {
                    ChickenCoop coop = new ChickenCoop(id, farmerId);
                    
                    // Parsear chickens
                    String chickensStr = extractArrayValue(coopStr, "chickens");
                    if (chickensStr != null) {
                        String[] chickensArray = chickensStr.split("\\},\\s*\\{");
                        for (String chickenStr : chickensArray) {
                            chickenStr = chickenStr.replaceAll("[\\[\\]{}]", "").trim();
                            if (chickenStr.isEmpty()) continue;
                            
                            int chickenId = extractIntValue(chickenStr, "id");
                            String name = extractStringValue(chickenStr, "name");
                            String color = extractStringValue(chickenStr, "color");
                            int age = extractIntValue(chickenStr, "age");
                            boolean molting = extractBooleanValue(chickenStr, "isMolting");
                            
                            if (chickenId != -1 && name != null) {
                                Chicken chicken = new Chicken(chickenId, name, color, age, molting);
                                coop.addChicken(chicken);
                            }
                        }
                    }
                    coopList.add(coop);
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing coops JSON: " + e.getMessage());
        }
        return coopList;
    }

    private int extractIntValue(String json, String key) {
        try {
            String pattern = "\"" + key + "\":\\s*(\\d+)";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
            java.util.regex.Matcher m = p.matcher(json);
            if (m.find()) {
                return Integer.parseInt(m.group(1));
            }
        } catch (Exception e) {
            // Ignorar error
        }
        return -1;
    }

    private String extractStringValue(String json, String key) {
        try {
            String pattern = "\"" + key + "\":\\s*\"([^\"]*)\"";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
            java.util.regex.Matcher m = p.matcher(json);
            if (m.find()) {
                return m.group(1);
            }
        } catch (Exception e) {
            // Ignorar error
        }
        return null;
    }

    private boolean extractBooleanValue(String json, String key) {
        try {
            String pattern = "\"" + key + "\":\\s*(true|false)";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
            java.util.regex.Matcher m = p.matcher(json);
            if (m.find()) {
                return Boolean.parseBoolean(m.group(1));
            }
        } catch (Exception e) {
            // Ignorar error
        }
        return false;
    }

    private String extractArrayValue(String json, String key) {
        try {
            String pattern = "\"" + key + "\":\\s*\\[([^\\]]*)\\]";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
            java.util.regex.Matcher m = p.matcher(json);
            if (m.find()) {
                return m.group(1);
            }
        } catch (Exception e) {
            // Ignorar error
        }
        return null;
    }

    private void saveData() {
        try {
            // Guardar granjeros en farmChickenData/farmers.json
            try (FileWriter writer = new FileWriter(DATA_DIR + "/farmers.json")) {
                writer.write(farmersToJson());
            }

            // Guardar gallineros en farmChickenData/coops.json
            try (FileWriter writer = new FileWriter(DATA_DIR + "/coops.json")) {
                writer.write(coopsToJson());
            }

            System.out.println("Data saved successfully to " + DATA_DIR);
        
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private String farmersToJson() {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < farmers.size(); i++) {
            Farmer farmer = farmers.get(i);
            json.append("  {\n");
            json.append("    \"id\": ").append(farmer.getId()).append(",\n");
            json.append("    \"name\": \"").append(farmer.getName()).append("\",\n");
            json.append("    \"coopIds\": ").append(farmer.getCoopIds().toString()).append("\n");
            json.append("  }");
            if (i < farmers.size() - 1) json.append(",");
            json.append("\n");
        }
        json.append("]");
        return json.toString();
    }

    private String coopsToJson() {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < coops.size(); i++) {
            ChickenCoop coop = coops.get(i);
            json.append("  {\n");
            json.append("    \"id\": ").append(coop.getId()).append(",\n");
            json.append("    \"farmerId\": ").append(coop.getFarmerId()).append(",\n");
            json.append("    \"chickens\": [\n");
            
            List<Chicken> chickens = coop.getChickens();
            for (int j = 0; j < chickens.size(); j++) {
                Chicken chicken = chickens.get(j);
                json.append("      {\n");
                json.append("        \"id\": ").append(chicken.getId()).append(",\n");
                json.append("        \"name\": \"").append(chicken.getName()).append("\",\n");
                json.append("        \"color\": \"").append(chicken.getColor()).append("\",\n");
                json.append("        \"age\": ").append(chicken.getAge()).append(",\n");
                json.append("        \"isMolting\": ").append(chicken.isMolting()).append("\n");
                json.append("      }");
                if (j < chickens.size() - 1) json.append(",");
                json.append("\n");
            }
            
            json.append("    ]\n");
            json.append("  }");
            if (i < coops.size() - 1) json.append(",");
            json.append("\n");
        }
        json.append("]");
        return json.toString();
    }

    // El resto de tus métodos se mantienen igual (mainMenu, farmerManagementMenu, etc.)
    public void mainMenu() {
        System.out.println("\n- - - Kevin Chalan's Chicken Farm Simulator - - -");

        int option = 0;
        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Farmer Management");
            System.out.println("2. Chicken Coop Management");
            System.out.println("3. Chicken Management");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    farmerManagementMenu();
                    break;
                case 2:
                    coopManagementMenu();
                    break;
                case 3:
                    chickenManagementMenu();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 4);
    }

    private void farmerManagementMenu() {
        int option = 0;
        do {
            System.out.println("\n--- FARMER MANAGEMENT ---");
            System.out.println("1. Create new farmer");
            System.out.println("2. Select current farmer");
            System.out.println("3. View all farmers");
            System.out.println("4. Back to main menu");

            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createFarmer();
                    break;
                case 2:
                    selectFarmer();
                    break;
                case 3:
                    viewFarmers();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 4);
    }

    private void createFarmer() {
        System.out.println("\n--- CREATE NEW FARMER ---");
        System.out.print("Farmer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Farmer Name: ");
        String name = scanner.nextLine();

        // Verificar si el ID ya existe
        if (farmers.stream().anyMatch(f -> f.getId() == id)) {
            System.out.println("Farmer ID already exists!");
            return;
        }

        Farmer farmer = new Farmer(id, name);
        farmers.add(farmer);
        System.out.println("Farmer '" + name + "' created successfully!");
        saveData(); // Guardar automáticamente
    }

    private void selectFarmer() {
        if (farmers.isEmpty()) {
            System.out.println("No farmers available. Create one first.");
            return;
        }

        System.out.println("\n--- SELECT FARMER ---");
        farmers.forEach(System.out::println);

        System.out.print("Enter Farmer ID to select: ");
        int farmerId = scanner.nextInt();
        scanner.nextLine();

        currentFarmer = farmers.stream()
                .filter(f -> f.getId() == farmerId)
                .findFirst()
                .orElse(null);

        if (currentFarmer != null) {
            System.out.println("Current farmer: " + currentFarmer.getName());
        } else {
            System.out.println("Farmer not found!");
        }
    }

    private void viewFarmers() {
        if (farmers.isEmpty()) {
            System.out.println("No farmers available.");
            return;
        }

        System.out.println("\n--- ALL FARMERS ---");
        farmers.forEach(System.out::println);
    }

    private void coopManagementMenu() {
        if (currentFarmer == null) {
            System.out.println("Please select a farmer first!");
            return;
        }

        int option = 0;
        do {
            System.out.println("\n--- COOP MANAGEMENT - Farmer: " + currentFarmer.getName() + " ---");
            System.out.println("1. Add chicken coop");
            System.out.println("2. View my coops");
            System.out.println("3. Back to main menu");

            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addChickenCoop();
                    break;
                case 2:
                    viewMyCoops();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 3);
    }

    private void addChickenCoop() {
        System.out.println("\n--- ADD CHICKEN COOP ---");
        System.out.print("Coop ID: ");
        int coopId = scanner.nextInt();
        scanner.nextLine();

        if (coops.stream().anyMatch(c -> c.getId() == coopId)) {
            System.out.println("Coop ID already exists!");
            return;
        }

        ChickenCoop coop = new ChickenCoop(coopId, currentFarmer.getId());
        coops.add(coop);
        currentFarmer.addCoop(coopId);
        System.out.println("Coop " + coopId + " added successfully!");
        saveData(); // Guardar automáticamente
    }

    private void viewMyCoops() {
        List<ChickenCoop> myCoops = coops.stream()
                .filter(coop -> coop.getFarmerId() == currentFarmer.getId())
                .toList();

        if (myCoops.isEmpty()) {
            System.out.println("You don't have any coops yet.");
            return;
        }

        System.out.println("\n--- MY COOPS - " + currentFarmer.getName() + " ---");
        myCoops.forEach(coop -> {
            System.out.println(coop);
            System.out.println("-".repeat(40));
        });
    }

    private void chickenManagementMenu() {
        if (currentFarmer == null) {
            System.out.println("Please select a farmer first!");
            return;
        }

        int option = 0;
        do {
            System.out.println("\n--- CHICKEN MANAGEMENT - Farmer: " + currentFarmer.getName() + " ---");
            System.out.println("1. Add chicken to coop");
            System.out.println("2. Make chicken do stuff");
            System.out.println("3. Back to main menu");

            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addChickenToCoop();
                    break;
                case 2:
                    makeChickenDoStuff();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 3);
    }

    private void addChickenToCoop() {
        List<ChickenCoop> myCoops = coops.stream()
                .filter(coop -> coop.getFarmerId() == currentFarmer.getId())
                .toList();

        if (myCoops.isEmpty()) {
            System.out.println("You don't have any coops. Create one first.");
            return;
        }

        System.out.println("\n--- ADD CHICKEN TO COOP ---");
        System.out.println("Your coops:");
        myCoops.forEach(coop -> 
            System.out.println("- Coop ID: " + coop.getId() + " (" + coop.getChickens().size() + " chickens)"));

        System.out.print("Enter coop ID: ");
        int coopId = scanner.nextInt();
        scanner.nextLine();

        ChickenCoop selectedCoop = myCoops.stream()
                .filter(coop -> coop.getId() == coopId)
                .findFirst()
                .orElse(null);

        if (selectedCoop == null) {
            System.out.println("Coop not found or you don't own it!");
            return;
        }

        System.out.println("\n--- NEW CHICKEN DETAILS ---");
        System.out.print("Chicken ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Color: ");
        String color = scanner.nextLine();
        
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Is the chicken molting? (true/false): ");
        boolean molting = scanner.nextBoolean();
        scanner.nextLine();

        // Verificar si el ID de gallina ya existe
        if (selectedCoop.getChickens().stream().anyMatch(ch -> ch.getId() == id)) {
            System.out.println("Chicken ID already exists in this coop!");
            return;
        }

        Chicken chicken = new Chicken(id, name, color, age, molting);
        selectedCoop.addChicken(chicken);
        System.out.println("Chicken '" + name + "' added to coop " + coopId + "!");
        saveData(); // Guardar automáticamente
    }

    private void makeChickenDoStuff() {
        List<ChickenCoop> myCoops = coops.stream()
                .filter(coop -> coop.getFarmerId() == currentFarmer.getId())
                .toList();

        if (myCoops.isEmpty()) {
            System.out.println("You don't have any coops.");
            return;
        }

        System.out.println("\n--- MAKE CHICKEN DO STUFF ---");
        System.out.println("Your coops:");
        myCoops.forEach(coop -> 
            System.out.println("- Coop ID: " + coop.getId() + " (" + coop.getChickens().size() + " chickens)"));

        System.out.print("Enter coop ID: ");
        int coopId = scanner.nextInt();
        scanner.nextLine();

        ChickenCoop selectedCoop = myCoops.stream()
                .filter(coop -> coop.getId() == coopId)
                .findFirst()
                .orElse(null);

        if (selectedCoop == null || selectedCoop.getChickens().isEmpty()) {
            System.out.println("No chickens in this coop!");
            return;
        }

        System.out.println("\nChickens in this coop:");
        selectedCoop.getChickens().forEach(System.out::println);

        System.out.print("Enter chicken ID: ");
        int chickenId = scanner.nextInt();
        scanner.nextLine();

        Chicken selectedChicken = selectedCoop.getChickens().stream()
                .filter(ch -> ch.getId() == chickenId)
                .findFirst()
                .orElse(null);

        if (selectedChicken != null) {
            System.out.println("\n--- " + selectedChicken.getName().toUpperCase() + " IS DOING STUFF ---");
            selectedChicken.doStuff();
        } else {
            System.out.println("Chicken not found!");
        }
    }
}