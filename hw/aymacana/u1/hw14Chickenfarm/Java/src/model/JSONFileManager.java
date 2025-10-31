package model;
/**
 *
 * @author Mateo Aymacaña
 */
import java.util.ArrayList;
import java.io.*;
import java.nio.file.*;

public class JSONFileManager {
    private static final String FILE_NAME = "chicken_farm_data.json";
    
    public static void saveToFile(ArrayList<ChickenCoop> coops) {
        try (FileWriter file = new FileWriter(FILE_NAME)) {
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{\n");
            jsonBuilder.append("  \"coops\": [\n");
            
            for (int i = 0; i < coops.size(); i++) {
                ChickenCoop coop = coops.get(i);
                jsonBuilder.append("    {\n");
                jsonBuilder.append("      \"id\": ").append(coop.getId()).append(",\n");
                jsonBuilder.append("      \"description\": \"").append(escapeJson(coop.getDescription())).append("\",\n");
                jsonBuilder.append("      \"chickens\": [\n");
                
                ArrayList<Chicken> chickens = coop.getChickens();
                for (int j = 0; j < chickens.size(); j++) {
                    Chicken chicken = chickens.get(j);
                    jsonBuilder.append("        {\n");
                    jsonBuilder.append("          \"id\": ").append(chicken.getId()).append(",\n");
                    jsonBuilder.append("          \"name\": \"").append(escapeJson(chicken.getName())).append("\",\n");
                    jsonBuilder.append("          \"color\": \"").append(escapeJson(chicken.getColor())).append("\",\n");
                    jsonBuilder.append("          \"age\": ").append(chicken.getAge()).append(",\n");
                    jsonBuilder.append("  \"isMolting\": ").append(chicken.isMolting()).append(",\n");
                    jsonBuilder.append("          \"coopId\": ").append(coop.getId()).append("\n");
                    jsonBuilder.append("        }");
                    
                    if (j < chickens.size() - 1) {
                        jsonBuilder.append(",");
                    }
                    jsonBuilder.append("\n");
                }
                
                jsonBuilder.append("      ]\n");
                jsonBuilder.append("    }");
                
                if (i < coops.size() - 1) {
                    jsonBuilder.append(",");
                }
                jsonBuilder.append("\n");
            }
            
            jsonBuilder.append("  ]\n");
            jsonBuilder.append("}");
            
            file.write(jsonBuilder.toString());
            System.out.println("Data saved successfully to " + FILE_NAME);
           
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    
    
    public static ArrayList<ChickenCoop> loadFromFile() {
        ArrayList<ChickenCoop> coops = new ArrayList<>();
        
        try {
            if (!Files.exists(Paths.get(FILE_NAME))) {
                System.out.println("No existing data file found. Starting with empty farm.");
                return coops;
            }
            
            String content = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            content = content.trim();
            
            if (!content.startsWith("{") || !content.endsWith("}")) {
                System.out.println("Invalid JSON format in file.");
                return coops;
            }
            
            int coopsStart = content.indexOf("\"coops\":[");
            if (coopsStart == -1) {
                System.out.println("No coops data found in file.");
                return coops;
            }
            
            coopsStart += 8; 
            int coopsEnd = findMatchingBracket(content, coopsStart);
            if (coopsEnd == -1) {
                System.out.println("Invalid coops array in file.");
                return coops;
            }
            
            String coopsArray = content.substring(coopsStart + 1, coopsEnd);
            parseCoopsArray(coopsArray, coops);
            
            System.out.println("Data loaded successfully from " + FILE_NAME);
            
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        
        return coops;
    }
    
    private static void parseCoopsArray(String coopsArray, ArrayList<ChickenCoop> coops) {
        try {
            int currentPos = 0;
            while (currentPos < coopsArray.length()) {
                int coopStart = coopsArray.indexOf("{", currentPos);
                if (coopStart == -1) break;
                
                int coopEnd = findMatchingBracket(coopsArray, coopStart);
                if (coopEnd == -1) break;
                
                String coopObject = coopsArray.substring(coopStart, coopEnd + 1);
                parseCoopObject(coopObject, coops);
                
                currentPos = coopEnd + 1;
            }
        } catch (Exception e) {
            System.out.println("Error parsing coops array: " + e.getMessage());
        }
    }
    
    private static void parseCoopObject(String coopObject, ArrayList<ChickenCoop> coops) {
        try {
            int idStart = coopObject.indexOf("\"id\":") + 5;
            int idEnd = coopObject.indexOf(",", idStart);
            if (idEnd == -1) idEnd = coopObject.indexOf("}", idStart);
            int coopId = Integer.parseInt(coopObject.substring(idStart, idEnd).trim());
            
            int descStart = coopObject.indexOf("\"description\":\"") + 15;
            int descEnd = coopObject.indexOf("\"", descStart);
            String description = unescapeJson(coopObject.substring(descStart, descEnd));
            
            ChickenCoop coop = new ChickenCoop(coopId, description);
            
            int chickensStart = coopObject.indexOf("\"chickens\":[");
            if (chickensStart != -1) {
                chickensStart += 12;
                int chickensEnd = findMatchingBracket(coopObject, chickensStart - 1);
                if (chickensEnd != -1) {
                    String chickensArray = coopObject.substring(chickensStart, chickensEnd);
                    parseChickensArray(chickensArray, coop);
                }
            }
            
            coops.add(coop);
            
        } catch (Exception e) {
            System.out.println("Error parsing coop object: " + e.getMessage());
        }
    }

    private static void parseChickensArray(String chickensArray, ChickenCoop coop) {
        try {
            int currentPos = 0;
            while (currentPos < chickensArray.length()) {
                int chickenStart = chickensArray.indexOf("{", currentPos);
                if (chickenStart == -1) break;
                
                int chickenEnd = findMatchingBracket(chickensArray, chickenStart);
                if (chickenEnd == -1) break;
                
                String chickenObject = chickensArray.substring(chickenStart, chickenEnd + 1);
                parseChickenObject(chickenObject, coop);
                
                currentPos = chickenEnd + 1;
            }
        } catch (Exception e) {
            System.out.println("Error parsing chickens array: " + e.getMessage());
        }
    }
    

    private static void parseChickenObject(String chickenObject, ChickenCoop coop) {
        try {
            int idStart = chickenObject.indexOf("\"id\":") + 5;
            int idEnd = chickenObject.indexOf(",", idStart);
            int chickenId = Integer.parseInt(chickenObject.substring(idStart, idEnd).trim());
            
            int nameStart = chickenObject.indexOf("\"name\":\"") + 8;
            int nameEnd = chickenObject.indexOf("\"", nameStart);
            String name = unescapeJson(chickenObject.substring(nameStart, nameEnd));
            
            int colorStart = chickenObject.indexOf("\"color\":\"") + 9;
            int colorEnd = chickenObject.indexOf("\"", colorStart);
            String color = unescapeJson(chickenObject.substring(colorStart, colorEnd));
            
            int ageStart = chickenObject.indexOf("\"age\":") + 5;
            int ageEnd = chickenObject.indexOf(",", ageStart);
            int age = Integer.parseInt(chickenObject.substring(ageStart, ageEnd).trim());
            
            int moltingStart = chickenObject.indexOf("\"isMolting\":") + 12;
            int moltingEnd = chickenObject.indexOf(",", moltingStart);
            if (moltingEnd == -1) moltingEnd = chickenObject.indexOf("}", moltingStart);
            boolean isMolting = Boolean.parseBoolean(chickenObject.substring(moltingStart, moltingEnd).trim());
            
            addChickenSilent(coop, new Chicken(chickenId, name, color, age, isMolting));
            
        } catch (Exception e) {
            System.out.println("Error parsing chicken object: " + e.getMessage());
        }
    }
    
    private static void addChickenSilent(ChickenCoop coop, Chicken chicken) {
        coop.getChickens().add(chicken);
    }
    
    private static int findMatchingBracket(String str, int start) {
        int count = 1;
        for (int i = start + 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '{' || c == '[') {
                count++;
            } else if (c == '}' || c == ']') {
                count--;
                if (count == 0) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    private static String escapeJson(String str) {
        if (str == null) return "";
        return str.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r")
                  .replace("\t", "\\t");
    }
    
    private static String unescapeJson(String str) {
        if (str == null) return "";
        return str.replace("\\\"", "\"")
                  .replace("\\\\", "\\")
                  .replace("\\n", "\n")
                  .replace("\\r", "\r")
                  .replace("\\t", "\t");
    }
    
    public static void displayFileData() {
        try {
            if (!Files.exists(Paths.get(FILE_NAME))) {
                System.out.println("No data file found. Please save data first.");
                return;
            }
            
            String content = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            System.out.println("\n=== JSON FILE DATA ===");
            System.out.println(content);
            
        } catch (Exception e) {
            System.out.println("Error displaying file data: " + e.getMessage());
        }
    }
    
    public static void deleteFile() {
        try {
            if (Files.exists(Paths.get(FILE_NAME))) {
                Files.delete(Paths.get(FILE_NAME));
                System.out.println("File " + FILE_NAME + " deleted successfully.");
            } else {
                System.out.println("File " + FILE_NAME + " does not exist.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting file: " + e.getMessage());
        }
    }
    
    public static void updateChickenData(ArrayList<ChickenCoop> coops, int chickenId, String newName, String newColor, int newAge, boolean newMoltingStatus) {
        try {
            boolean chickenFound = false;
            ChickenCoop foundCoop = null;
            
            for (ChickenCoop coop : coops) {
                ArrayList<Chicken> chickens = coop.getChickens();
                for (Chicken chicken : chickens) {
                    if (chicken.getId() == chickenId) {
                        chicken.setName(newName);
                        chicken.setColor(newColor);
                        chicken.setAge(newAge);
                        chicken.setMolting(newMoltingStatus);
                        chickenFound = true;
                        foundCoop = coop;
                        break;
                    }
                }
                if (chickenFound) break;
            }
            
            if (chickenFound) {

                saveToFile(coops);
                System.out.println("Chicken with ID " + chickenId + " in Coop " + foundCoop.getId() + " updated successfully.");
            } else {
                System.out.println("Chicken with ID " + chickenId + " not found in any coop.");
            }
            
        } catch (Exception e) {
            System.out.println("Error updating chicken data: " + e.getMessage());
        }
    }
    
    public static void deleteChickenFromFile(ArrayList<ChickenCoop> coops, int coopId, int chickenId) {
        try {
            boolean chickenFound = false;
            ChickenCoop foundCoop = null;
            
            for (ChickenCoop coop : coops) {
                if (coop.getId() == coopId) {
                    foundCoop = coop;
                    ArrayList<Chicken> chickens = coop.getChickens();
                    for (int i = 0; i < chickens.size(); i++) {
                        Chicken chicken = chickens.get(i);
                        if (chicken.getId() == chickenId) {
                            chickens.remove(i);
                            chickenFound = true;
                            break;
                        }
                    }
                    break;
                }
            }
            
            if (chickenFound) {
                saveToFile(coops);
                System.out.println("Chicken with ID " + chickenId + " deleted from Coop " + coopId + " and JSON file updated successfully.");
            } else {
                if (foundCoop == null) {
                    System.out.println("Coop with ID " + coopId + " not found.");
                } else {
                    System.out.println("Chicken with ID " + chickenId + " not found in Coop " + coopId + ".");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error deleting chicken from JSON: " + e.getMessage());
        }
    }
    
    public static boolean fileExists() {
        return Files.exists(Paths.get(FILE_NAME));
    }
}