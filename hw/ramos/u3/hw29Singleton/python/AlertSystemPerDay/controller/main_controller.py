from datetime import date
from model.task import Task
from model.task_repository import TaskRepository
from model.alert_config_singleton import AlertConfigSingleton
from view.main_view import MainView


class MainController:

    def __init__(self):
        self.repository = TaskRepository()
        self.config = AlertConfigSingleton.get_instance()
        self.tasks = self.repository.load_tasks()

        self.view = MainView(self)
        self.refresh_view()
        self.check_alerts()

        self.view.run()  

    def add_task(self, name: str, date_text: str):
        try:
            task_date = date.fromisoformat(date_text)  
            task = Task(name, task_date)

            self.tasks.append(task)
            self.repository.save_tasks(self.tasks)

            self.refresh_view()
            self.check_alerts()

        except ValueError:
            self.view.show_alert("Invalid date format. Use YYYY-MM-DD")

    def update_alert_days(self, days: str):
        try:
            value = int(days)
            self.config.set_alert_days(value)
            self.check_alerts()
        except ValueError:
            self.view.show_alert("Enter a valid number")

    def refresh_view(self):
        text = ""
        for task in self.tasks:
            text += str(task) + "\n"
        self.view.update_task_list(text)

    def check_alerts(self):
        for task in self.tasks:
            if task.get_remaining_days() <= self.config.get_alert_days():
                self.view.show_alert(
                    f'The task "{task.get_name()}" wins in {task.get_remaining_days()} days'
                )


if __name__ == "__main__":
    MainController()
