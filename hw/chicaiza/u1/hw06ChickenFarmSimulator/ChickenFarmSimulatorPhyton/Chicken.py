# chicken.py (modelo)

class Chicken:
    # Constructor vacío
    def __init__(self, name=None, color=None, age=None, is_molting=None):
        self._name = name
        self._color = color
        self._age = age
        self._is_molting = is_molting

    # Getters y Setters
    def get_name(self):
        return self._name

    def set_name(self, name):
        self._name = name

    def get_color(self):
        return self._color

    def set_color(self, color):
        self._color = color

    def get_age(self):
        return self._age

    def set_age(self, age):
        self._age = age

    def get_is_molting(self):
        return self._is_molting

    def set_is_molting(self, is_molting):
        self._is_molting = is_molting

    # Método para imprimir datos
    def __str__(self):
        return f"Chicken(name='{self._name}', color='{self._color}', age={self._age}, is_molting={self._is_molting})"
