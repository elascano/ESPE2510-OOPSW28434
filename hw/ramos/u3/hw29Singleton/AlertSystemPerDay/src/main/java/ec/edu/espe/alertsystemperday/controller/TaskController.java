package ec.edu.espe.alertsystemperday.controller;

/**
 *
 * @author Paulo Ramos
 */

import ec.edu.espe.alertsystemperday.model.AlertConfig;
import ec.edu.espe.alertsystemperday.model.Task;
import ec.edu.espe.alertsystemperday.model.TaskRepository;
import ec.edu.espe.alertsystemperday.view.MainView;
import java.time.LocalDate;
import java.util.List;

public class TaskController {

    private List<Task> tasks;
    private AlertConfig config;
    private TaskRepository repository;
    private MainView view;

    public TaskController() {
        repository = new TaskRepository();
        tasks = repository.loadTasks();
        config = new AlertConfig(repository.loadAlertDays());
        view = new MainView(this);
        refreshView();
        checkAlerts();
    }

    public void addTask(String name, LocalDate date) {
        tasks.add(new Task(name, date));
        repository.save(tasks, config.getAlertDays());
        refreshView();
        checkAlerts();
    }

    public void updateAlertDays(int days) {
        config.setAlertDays(days);
        repository.save(tasks, config.getAlertDays());
        checkAlerts();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getAlertDays() {
        return config.getAlertDays();
    }

    private void refreshView() {
        view.showTasks(tasks, config.getAlertDays());
    }

    private void checkAlerts() {
        for (Task t : tasks) {
            if (t.getDaysRemaining() <= config.getAlertDays()) {
                view.showAlert(t.getName(), t.getDaysRemaining());
            }
        }
    }
}
