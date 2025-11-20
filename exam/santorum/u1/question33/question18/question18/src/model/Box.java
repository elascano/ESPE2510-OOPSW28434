package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


/**
 *
 * @author Thais Santórum Team 6 - Paradigm, @ESPE
 */

public class Box {

    private static final String FILE = "data.json";


    public static void save(ArrayList<Scissors> scissorsS) {
        try (FileWriter writer = new FileWriter(FILE)) {
            Gson gson = new Gson();
            gson.toJson(scissorsS, writer);
        } catch (Exception e) {
            System.out.println("Error in save: " + e.getMessage());
        }
    }


    public static ArrayList<Scissors> load() {
        try (FileReader reader = new FileReader(FILE)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<ArrayList<Scissors>>(){}.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
