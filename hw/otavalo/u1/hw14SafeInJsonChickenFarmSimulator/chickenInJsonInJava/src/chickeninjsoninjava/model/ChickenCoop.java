package chickeninjsoninjava.model;

/**
 *
 * @author Arelys
 */

import java.io.*;
import java.util.*;

public class ChickenCoop {
    private ArrayList<Chicken> chickens;
    private String filename;

    public ChickenCoop(String filename) {
        this.filename = filename;
        this.chickens = new ArrayList<>();
        loadFromJson();
    }

    public ChickenCoop() {
        this("chickens.json");
    }

    public void addChicken(Chicken chicken) {
        chickens.add(chicken);
        saveToJson();
    }

    public ArrayList<Chicken> getChickens() {
        return chickens;
    }

    public Chicken findChickenById(String chickenId) {
        for (Chicken chicken : chickens) {
            if (chicken.getId().equals(chickenId)) {
                return chicken;
            }
        }
        return null;
    }

    public boolean editChicken(String chickenId, String newName, String newColor, int newAge, boolean newIsMolting) {
        Chicken chicken = findChickenById(chickenId);
        if (chicken != null) {
            chicken.setName(newName);
            chicken.setColor(newColor);
            chicken.setAge(newAge);
            chicken.setMolting(newIsMolting);
            saveToJson();
            return true;
        }
        return false;
    }

    public boolean deleteChicken(String chickenId) {
        int initialSize = chickens.size();
        chickens.removeIf(c -> c.getId().equals(chickenId));
        if (chickens.size() < initialSize) {
            saveToJson();
            return true;
        }
        return false;
    }

    public void saveToJson() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("[\n");
            for (int i = 0; i < chickens.size(); i++) {
                writer.write("  " + chickens.get(i).toJson());
                if (i < chickens.size() - 1) writer.write(",");
                writer.write("\n");
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("Error saving data to " + filename);
        }
    }

    public void loadFromJson() {
        chickens = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("{") && (line.endsWith("}") || line.endsWith("},"))) {
                    line = line.replace(",", "");
                    Chicken c = Chicken.fromJson(line);
                    if (c != null) chickens.add(c);
                }
            }

            int maxId = 0;
            for (Chicken c : chickens) {
                try {
                    int current = Integer.parseInt(c.getId());
                    if (current > maxId) maxId = current;
                } catch (NumberFormatException e) {
                }
            }
            Chicken.setNextId(maxId + 1);

        } catch (FileNotFoundException e) {
            chickens = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error loading " + filename);
        }
    }
}
