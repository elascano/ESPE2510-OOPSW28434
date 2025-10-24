class Chicken:
    def __init__(self, name=None, color=None, age=None, is_molting=None):
        self._name = name
        self._color = color
        self._age = age
        self._is_molting = is_molting

    def __str__(self):
        return f"Chicken(name='{self._name}', color='{self._color}', age={self._age}, is_molting={self._is_molting})"