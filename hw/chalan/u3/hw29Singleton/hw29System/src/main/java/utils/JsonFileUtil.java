package utils;

import ec.edu.espe.hw29system.model.Photographer;
import com.google.gson.Gson;
import java.io.FileWriter;

public class JsonFileUtil {

    private static JsonFileUtil instance;
    private final Gson gson = new Gson();

    private JsonFileUtil() {}

    public static JsonFileUtil getInstance() {
        if (instance == null) {
            instance = new JsonFileUtil();
        }
        return instance;
    }

    public void save(Photographer photographer) {
        try (FileWriter writer = new FileWriter("photographers.json", true)) {
            writer.write(gson.toJson(photographer) + "\n");
        } catch (Exception e) {
            System.err.println("Error saving JSON");
        }
    }
}
