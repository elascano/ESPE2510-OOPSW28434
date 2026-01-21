from datetime import date
from model.task import Task


class TaskRepository:
    _file_path = "tasks.json"

    def load_tasks(self) -> list[Task]:
        tasks = []

        try:
            with open(self._file_path, "r") as file:
                for line in file:
                    parts = line.strip().split(";")
                    if len(parts) == 2:
                        task_name = parts[0]
                        task_date = date.fromisoformat(parts[1])
                        tasks.append(Task(task_name, task_date))
        except FileNotFoundError:
            pass
        except Exception as e:
            print("Error loading tasks:", e)

        return tasks

    def save_tasks(self, tasks: list[Task]):
        try:
            with open(self._file_path, "w") as file:
                for task in tasks:
                    file.write(f"{task.get_name()};{task.get_due_date()}\n")
        except Exception as e:
            print("Error saving tasks:", e)
