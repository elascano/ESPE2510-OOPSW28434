// model/TaskRepository.js
const fs = require('fs');
const path = require('path');
const Task = require('./Task');

class TaskRepository {
    constructor() {
        this.filePath = path.join(__dirname, '../data/tasks.json');
    }

    loadTasks() {
        if (!fs.existsSync(this.filePath)) return [];
        const data = JSON.parse(fs.readFileSync(this.filePath));
        return data.map(t => new Task(t.name, t.dueDate));
    }

    saveTasks(tasks) {
        fs.mkdirSync(path.dirname(this.filePath), { recursive: true });
        fs.writeFileSync(
            this.filePath,
            JSON.stringify(tasks.map(t => ({
                name: t.getName(),
                dueDate: t.getDueDate()
            })), null, 2)
        );
    }
}

module.exports = TaskRepository;
