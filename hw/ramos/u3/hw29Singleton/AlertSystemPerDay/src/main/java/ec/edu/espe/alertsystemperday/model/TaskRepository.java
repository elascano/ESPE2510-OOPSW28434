package ec.edu.espe.alertsystemperday.model;

/**
 *
 * @author Paulo Ramos
 */

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class TaskRepository {

    private static final String FILE = "data.json";

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            if (!Files.exists(new File(FILE).toPath())) return tasks;

            String content = new String(Files.readAllBytes(new File(FILE).toPath()));
            JSONObject root = new JSONObject(content);

            JSONArray array = root.getJSONArray("tasks");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                tasks.add(new Task(
                        obj.getString("name"),
                        LocalDate.parse(obj.getString("dueDate"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public int loadAlertDays() {
        try {
            if (!Files.exists(new File(FILE).toPath())) return 3;

            String content = new String(Files.readAllBytes(new File(FILE).toPath()));
            return new JSONObject(content).getInt("alertDays");
        } catch (Exception e) {
            return 3;
        }
    }

    public void save(List<Task> tasks, int alertDays) {
        try {
            JSONArray array = new JSONArray();
            for (Task t : tasks) {
                JSONObject obj = new JSONObject();
                obj.put("name", t.getName());
                obj.put("dueDate", t.getDueDate().toString());
                array.put(obj);
            }

            JSONObject root = new JSONObject();
            root.put("alertDays", alertDays);
            root.put("tasks", array);

            FileWriter fw = new FileWriter(FILE);
            fw.write(root.toString(4));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
