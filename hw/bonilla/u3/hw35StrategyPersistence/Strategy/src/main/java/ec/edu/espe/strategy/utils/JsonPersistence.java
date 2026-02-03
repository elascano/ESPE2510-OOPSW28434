package ec.edu.espe.strategy.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.strategy.model.Parking;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class JsonPersistence implements Persistence {
    private final String fileName = "data/parking.json";
    private final Gson gson;

    public JsonPersistence() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsString());
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.toString());
                    }
                })
                .setPrettyPrinting()
                .create();
    }

    public boolean create(Parking parking) {
        List<Parking> list = read();
        list.add(parking);
        return save(list);
    }

    public List<Parking> read() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating JSON file: " + e.getMessage());
            }
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            List<Parking> list = gson.fromJson(reader, new TypeToken<List<Parking>>() {}.getType());
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error reading JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean update(String id, Parking parking) {
        List<Parking> list = read();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                list.set(i, parking);
                return save(list);
            }
        }
        return false;
    }

    public boolean delete(String id) {
        List<Parking> list = read();
        list.removeIf(p -> p.getId().equals(id));
        return save(list);
    }

    public Parking find(String id) {
        return read().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    private boolean save(List<Parking> list) {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(list, writer);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving JSON: " + e.getMessage());
            return false;
        }
    }
}