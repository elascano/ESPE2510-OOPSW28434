# kevin chalan NRC 28434
# chicken.py
class Chicken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self._id = id
        self._name = name
        self._color = color
        self._age = age
        self._is_molting = is_molting

    def __str__(self):
        return (f"\nChicken:"
                f"\n  id --> {self._id}"
                f"\n  name --> {self._name}"
                f"\n  color --> {self._color}"
                f"\n  age --> {self._age}"
                f"\n  isMolting --> {self._is_molting}")

    # Getters
    def get_id(self):
        return self._id

    def get_name(self):
        return self._name

    def get_color(self):
        return self._color

    def get_age(self):
        return self._age

    def is_molting(self):
        return self._is_molting

    # Setters
    def set_id(self, id: int):
        self._id = id

    def set_name(self, name: str):
        self._name = name

    def set_color(self, color: str):
        self._color = color

    def set_age(self, age: int):
        self._age = age

    def set_is_molting(self, is_molting: bool):
        self._is_molting = is_molting
