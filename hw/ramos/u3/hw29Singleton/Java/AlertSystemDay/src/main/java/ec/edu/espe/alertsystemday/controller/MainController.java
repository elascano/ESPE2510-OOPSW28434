package ec.edu.espe.alertsystemday.controller;

/**
 *
 * @author Paulo Ramos
 */
import ec.edu.espe.alertsystemday.model.*;
import ec.edu.espe.alertsystemday.view.MainView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ec.edu.espe.alertsystemday.model.Task;

public class MainController {

    private MainView view;
    private List<Task> tasks;
    private TaskRepository repository;
    private AlertConfigSingleton config;

    public MainController() {
        repository = new TaskRepository();
        config = AlertConfigSingleton.getInstance();
        tasks = repository.loadTasks();
        view = new MainView(this);
        view.setVisible(true);
        refreshView();
        checkAlerts();
    }

    public void addTask(String name, String dateText) {
        try {
            LocalDate date = LocalDate.parse(dateText);
            Task task = new Task(name, date);
            tasks.add(task);
            repository.saveTasks(tasks);
            refreshView();
            checkAlerts();
        } catch (Exception e) {
            view.showAlert("Formato de fecha inválido. Use YYYY-MM-DD");
        }
    }

    public void updateAlertDays(String days) {
        int value = Integer.parseInt(days);
        config.setAlertDays(value);
        checkAlerts();
    }

    private void refreshView() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task).append("\n");
        }
        view.updateTaskList(sb.toString());
    }

    private void checkAlerts() {
        for (Task task : tasks) {
            if (task.getRemainingDays() <= config.getAlertDays()) {
                view.showAlert("The task \"" + task.getName()
                        + "\" wins in " + task.getRemainingDays() + " days");
            }
        }
    }

    public static void main(String[] args) {
        new MainController();
    }
}
