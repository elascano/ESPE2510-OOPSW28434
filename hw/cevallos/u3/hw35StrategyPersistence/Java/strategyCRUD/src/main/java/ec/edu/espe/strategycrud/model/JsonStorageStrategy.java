/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.strategycrud.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author Mateo Cevallos
 */
public class JsonStorageStrategy implements StorageStrategy{

    private static final String FILE_PATH = "events.json";
    private final Gson gson;

    public JsonStorageStrategy() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public boolean addEvent(Event event) {
        List<Event> events = loadEvents();
        events.add(event);
        return saveEvents(events);
    }

    @Override
    public boolean updateEvent(Event event) {
        List<Event> events = loadEvents();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equals(event.getId())) {
                events.set(i, event);
                return saveEvents(events);
            }
        }
        return false;
    }

    @Override
    public boolean deleteEvent(String id) {
        List<Event> events = loadEvents();
        events.removeIf(e -> e.getId().equals(id));
        return saveEvents(events);
    }

    @Override
    public Event readEvent(String id) {
        List<Event> events = loadEvents();
        return events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private List<Event> loadEvents() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(FILE_PATH)) {
            Type eventListType = new TypeToken<ArrayList<Event>>() {
            }.getType();
            List<Event> events = gson.fromJson(reader, eventListType);
            return events != null ? events : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private boolean saveEvents(List<Event> events) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(events, writer);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
