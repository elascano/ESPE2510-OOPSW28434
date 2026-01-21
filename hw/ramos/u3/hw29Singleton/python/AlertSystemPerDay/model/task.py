from datetime import date


class Task:

    def __init__(self, name: str, due_date: date):
        self._name = name
        self._due_date = due_date

    def get_name(self) -> str:
        return self._name

    def get_due_date(self) -> date:
        return self._due_date

    def get_remaining_days(self) -> int:
        return (self._due_date - date.today()).days

    def __str__(self) -> str:
        return f"{self._name} - Win in {self.get_remaining_days()} days"
