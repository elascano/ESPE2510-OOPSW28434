package ec.edu.espe.instrument.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.instrument.model.Instrument;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class JsonPersistence implements Persistence {

    private static final String FILE_NAME = "instruments.json";
    private Gson gson;

    public JsonPersistence() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public boolean create(Instrument instrument) {
        List<Instrument> instruments = read();
        for (Instrument t : instruments) {
            if (t.getId().equals(instrument.getId())) {
                return false;
            }
        }
        instruments.add(instrument);
        return saveAll(instruments);
    }

    @Override
    public List<Instrument> read() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Instrument>>() {}.getType();
            List<Instrument> instruments = gson.fromJson(reader, listType);
            return instruments != null ? instruments : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error reading JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(String id, Instrument instrument) {
        List<Instrument> instruments = read();
        boolean found = false;
        for (int i = 0; i < instruments.size(); i++) {
            if (instruments.get(i).getId().equals(id)) {
                instruments.set(i, instrument);
                found = true;
                break;
            }
        }
        if (found) {
            return saveAll(instruments);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        List<Instrument> instruments = read();
        boolean removed = instruments.removeIf(t -> t.getId().equals(id));
        if (removed) {
            return saveAll(instruments);
        }
        return false;
    }

    @Override
    public Instrument find(String id) {
        List<Instrument> instruments = read();
        for (Instrument t : instruments) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    private boolean saveAll(List<Instrument> instruments) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(instruments, writer);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing JSON: " + e.getMessage());
            return false;
        }
    }
}