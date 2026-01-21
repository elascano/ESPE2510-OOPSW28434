
const Task = require('../model/Task');
const TaskRepository = require('../model/TaskRepository');
const AlertConfigSingleton = require('../model/AlertConfigSingleton');

class MainController {
    constructor() {
        this.repository = new TaskRepository();
        this.config = AlertConfigSingleton.getInstance();
        this.tasks = this.repository.loadTasks();
    }

    getTasks() {
        return this.tasks.map(t => ({
            name: t.getName(),
            remainingDays: t.getRemainingDays()
        }));
    }

    addTask(name, dueDate) {
        const task = new Task(name, dueDate);
        this.tasks.push(task);
        this.repository.saveTasks(this.tasks);
    }

    updateAlertDays(days) {
        this.config.setAlertDays(days);
    }
}

module.exports = MainController;
