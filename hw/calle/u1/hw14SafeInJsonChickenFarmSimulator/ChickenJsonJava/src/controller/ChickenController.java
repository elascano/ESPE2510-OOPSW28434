package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Chicken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChickenController {
    private static final String FILE_PATH = "data/chickens.json";
    private final Gson gson = new Gson();
    
private int getNextId() {
        List<Chicken> chickens = readChickens();
        int maxId = chickens.stream()
                .mapToInt(Chicken::getId) 
                .max()                     
                .orElse(0);                
        return maxId + 1;
    }

    public List<Chicken> readChickens() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Chicken>>() {}.getType();
            List<Chicken> chickens = gson.fromJson(reader, listType);
            return chickens != null ? chickens : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void writeChickens(List<Chicken> chickens) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(chickens, writer);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void addChicken(Chicken chicken) {
        List<Chicken> chickens = readChickens();
        chickens.add(chicken);
        writeChickens(chickens);
    }

    public Chicken findChicken(int id) {
        return readChickens().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean deleteChicken(int id) {
        List<Chicken> chickens = readChickens();
        boolean removed = chickens.removeIf(c -> c.getId() == id);
        if (removed) writeChickens(chickens);
        return removed;
    }

    public boolean updateChicken(Chicken updatedChicken) {
        List<Chicken> chickens = readChickens();
        for (int i = 0; i < chickens.size(); i++) {
            if (chickens.get(i).getId() == updatedChicken.getId()) {
                chickens.set(i, updatedChicken);
                writeChickens(chickens);
                return true;
            }
        }
        return false;
    }
}