package chickenfarmsimulator.controller;

import chickenfarmsimulator.model.Chicken;
import chickenfarmsimulator.model.ChickenCoop;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FarmController {
    private List<ChickenCoop> coops;
    private static final String DATA_FILE = "data/farms.json";
    
    public FarmController() {
        this.coops = new ArrayList<>();
        loadData();
    }
    
    private void loadData() {
        try {
            File file = new File(DATA_FILE);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                StringBuilder json = new StringBuilder();
                while (scanner.hasNextLine()) {
                    json.append(scanner.nextLine());
                }
                scanner.close();
                parseJson(json.toString());
            }
        } catch (Exception e) {
            System.out.println("No existing data found, starting fresh.");
        }
    }
    
    private void parseJson(String json) {
        try {
            if (json.contains("\"coops\"")) {
                String coopsSection = json.substring(json.indexOf("[") + 1, json.lastIndexOf("]"));
                String[] coopEntries = coopsSection.split("\\},\\s*\\{");
                
                for (String coopEntry : coopEntries) {
                    coopEntry = coopEntry.replace("{", "").replace("}", "").trim();
                    
                    int id = Integer.parseInt(extractValue(coopEntry, "\"id\":"));
                    
                    ChickenCoop coop = new ChickenCoop(id);
                    
                    if (coopEntry.contains("\"chickens\"")) {
                        String chickensSection = coopEntry.substring(coopEntry.indexOf("[") + 1, coopEntry.lastIndexOf("]"));
                        String[] chickenEntries = chickensSection.split("\\},\\s*\\{");
                        
                        for (String chickenEntry : chickenEntries) {
                            chickenEntry = chickenEntry.replace("{", "").replace("}", "").trim();
                            
                            int chickenId = Integer.parseInt(extractValue(chickenEntry, "\"id\":"));
                            String name = extractValue(chickenEntry, "\"name\":").replace("\"", "");
                            String color = extractValue(chickenEntry, "\"color\":").replace("\"", "");
                            int age = Integer.parseInt(extractValue(chickenEntry, "\"age\":"));
                            boolean isMolting = Boolean.parseBoolean(extractValue(chickenEntry, "\"isMolting\":"));
                            
                            Chicken chicken = new Chicken(chickenId, name, color, age, isMolting);
                            coop.addChicken(chicken);
                        }
                    }
                    coops.add(coop);
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing JSON data: " + e.getMessage());
        }
    }
    
    private String extractValue(String text, String key) {
        int start = text.indexOf(key) + key.length();
        int end = text.indexOf(",", start);
        if (end == -1) end = text.indexOf("}", start);
        if (end == -1) end = text.length();
        return text.substring(start, end).trim();
    }
    
    private void saveData() {
        try {
            new File("data").mkdirs();
            FileWriter writer = new FileWriter(DATA_FILE);
            writer.write("{\n");
            writer.write("  \"coops\": [\n");
            
            for (int i = 0; i < coops.size(); i++) {
                ChickenCoop coop = coops.get(i);
                writer.write("    {\n");
                writer.write("      \"id\": " + coop.getId() + ",\n");
                writer.write("      \"chickens\": [\n");
                
                List<Chicken> chickens = coop.getChickens();
                for (int j = 0; j < chickens.size(); j++) {
                    Chicken chicken = chickens.get(j);
                    writer.write("        {\n");
                    writer.write("          \"id\": " + chicken.getId() + ",\n");
                    writer.write("          \"name\": \"" + chicken.getName() + "\",\n");
                    writer.write("          \"color\": \"" + chicken.getColor() + "\",\n");
                    writer.write("          \"age\": " + chicken.getAge() + ",\n");
                    writer.write("          \"isMolting\": " + chicken.isMolting() + "\n");
                    writer.write("        }");
                    if (j < chickens.size() - 1) writer.write(",");
                    writer.write("\n");
                }
                
                writer.write("      ]\n");
                writer.write("    }");
                if (i < coops.size() - 1) writer.write(",");
                writer.write("\n");
            }
            
            writer.write("  ]\n");
            writer.write("}\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    
    public boolean createCoop(int coopId) {
        for (ChickenCoop coop : coops) {
            if (coop.getId() == coopId) {
                return false;
            }
        }
        coops.add(new ChickenCoop(coopId));
        saveData();
        return true;
    }
    
    public boolean addChicken(int coopId, int chickenId, String name, String color, int age, boolean isMolting) {
        ChickenCoop coop = getCoop(coopId);
        if (coop == null) return false;
        
        if (coop.getChicken(chickenId) != null) return false;
        
        Chicken chicken = new Chicken(chickenId, name, color, age, isMolting);
        coop.addChicken(chicken);
        saveData();
        return true;
    }
    
    public ChickenCoop getCoop(int coopId) {
        for (ChickenCoop coop : coops) {
            if (coop.getId() == coopId) {
                return coop;
            }
        }
        return null;
    }
    
    public List<ChickenCoop> getAllCoops() {
        return coops;
    }
    
    public boolean updateChicken(int coopId, int chickenId, String name, String color, int age, boolean isMolting) {
        ChickenCoop coop = getCoop(coopId);
        if (coop == null) return false;
        
        Chicken chicken = coop.getChicken(chickenId);
        if (chicken == null) return false;
        
        chicken.setName(name);
        chicken.setColor(color);
        chicken.setAge(age);
        chicken.setMolting(isMolting);
        saveData();
        return true;
    }
    
    public boolean deleteChicken(int coopId, int chickenId) {
        ChickenCoop coop = getCoop(coopId);
        if (coop == null) return false;
        
        coop.removeChicken(chickenId);
        saveData();
        return true;
    }
    
    public boolean performAction(int coopId, int chickenId, int action) {
        ChickenCoop coop = getCoop(coopId);
        if (coop == null) return false;
        
        Chicken chicken = coop.getChicken(chickenId);
        if (chicken == null) return false;
        
        switch (action) {
            case 1: chicken.cluck(); break;
            case 2: chicken.wander(); break;
            case 3: chicken.eat(); break;
            case 4: chicken.drink(); break;
            case 5: chicken.poop(); break;
            case 6: chicken.layEgg(); break;
            default: return false;
        }
        return true;
    }
    
    public int getChickenCount() {
        int count = 0;
        for (ChickenCoop coop : coops) {
            count += coop.getChickens().size();
        }
        return count;
    }
    
    public int getCoopCount() {
        return coops.size();
    }
}