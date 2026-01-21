package ec.edu.espe.alertsystemday.model;

/**
 *
 * @author Paulo Ramos
 */

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private static final String FILE_PATH = "tasks.json";

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                tasks.add(new Task(parts[0], LocalDate.parse(parts[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.getName() + ";" + task.getDueDate());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
