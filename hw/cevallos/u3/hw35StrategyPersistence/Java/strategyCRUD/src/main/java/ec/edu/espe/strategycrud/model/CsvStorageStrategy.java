/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.strategycrud.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateo Cevallos
 */
public class CsvStorageStrategy implements StorageStrategy {

    private static final String FILE_PATH = "events.csv";
    private static final String DELIMITER = ",";
    private static final String HEADER = "id,name,date";

    @Override
    public boolean addEvent(Event event) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {

            File file = new File(FILE_PATH);
            if (file.length() == 0) {
                out.println(HEADER);
            }

            out.println(event.getId() + DELIMITER
                    + event.getName() + DELIMITER
                    + event.getDate());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean updateEvent(Event event) {
        List<Event> events = loadEvents();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equals(event.getId())) {
                events.set(i, event);
                return saveAllEvents(events);
            }
        }
        return false;
    }

    @Override
    public boolean deleteEvent(String id) {
        List<Event> events = loadEvents();
        events.removeIf(e -> e.getId().equals(id));
        return saveAllEvents(events);
    }

    @Override
    public Event readEvent(String id) {
        return loadEvents().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private List<Event> loadEvents() {
        List<Event> events = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return events;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] values = line.split(DELIMITER);
                if (values.length >= 3) {
                    Event event = new Event(values[0], values[1], values[2]);
                    events.add(event);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return events;
    }

    private boolean saveAllEvents(List<Event> events) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH))) {
            out.println(HEADER);
            for (Event event : events) {
                out.println(event.getId() + DELIMITER
                        + event.getName() + DELIMITER
                        + event.getDate());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
