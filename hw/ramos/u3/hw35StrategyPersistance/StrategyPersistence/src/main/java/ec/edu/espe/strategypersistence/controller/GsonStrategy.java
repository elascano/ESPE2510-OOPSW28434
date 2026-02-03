package ec.edu.espe.strategypersistence.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.strategypersistence.model.Store;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Ramos
 */

public class GsonStrategy implements PersistenceStrategy {
    private final String path = "Store.json";
    private final Gson gson;

    public GsonStrategy() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    private List<Store> readFile() {
        try (Reader reader = new FileReader(path)) {
            Type listType = new TypeToken<ArrayList<Store>>(){}.getType();
            List<Store> data = gson.fromJson(reader, listType);
            return (data != null) ? data : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveFile(List<Store> list) {
        try (Writer writer = new FileWriter(path)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Store store) {
        List<Store> list = readFile();
        list.add(store);
        saveFile(list);
    }

    @Override
    public Store find(int id) {
        return readFile().stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(int id, Store store) {
        List<Store> list = readFile();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.set(i, store);
                break;
            }
        }
        saveFile(list);
    }

    @Override
    public void delete(int id) {
        List<Store> list = readFile();
        list.removeIf(s -> s.getId() == id);
        saveFile(list);
    }

    @Override
    public List<Store> loadAll() {
        return readFile();
    }
}