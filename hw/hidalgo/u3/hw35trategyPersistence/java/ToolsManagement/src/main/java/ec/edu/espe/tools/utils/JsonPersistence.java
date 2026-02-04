package ec.edu.espe.tools.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.tools.model.Tool;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mikael Hidalgo, Paradigm, @ESPE
 */
public class JsonPersistence implements Persistence {

    private static final String FILE_NAME = "tools.json";
    private Gson gson;

    public JsonPersistence() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public boolean create(Tool tool) {
        List<Tool> tools = read();
        for (Tool t : tools) {
            if (t.getId().equals(tool.getId())) {
                return false;
            }
        }
        tools.add(tool);
        return saveAll(tools);
    }

    @Override
    public List<Tool> read() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Tool>>() {}.getType();
            List<Tool> tools = gson.fromJson(reader, listType);
            return tools != null ? tools : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error reading JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(String id, Tool tool) {
        List<Tool> tools = read();
        boolean found = false;
        for (int i = 0; i < tools.size(); i++) {
            if (tools.get(i).getId().equals(id)) {
                tools.set(i, tool);
                found = true;
                break;
            }
        }
        if (found) {
            return saveAll(tools);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        List<Tool> tools = read();
        boolean removed = tools.removeIf(t -> t.getId().equals(id));
        if (removed) {
            return saveAll(tools);
        }
        return false;
    }

    @Override
    public Tool find(String id) {
        List<Tool> tools = read();
        for (Tool t : tools) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    private boolean saveAll(List<Tool> tools) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(tools, writer);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing JSON: " + e.getMessage());
            return false;
        }
    }
}