from egg import Egg


class Chicken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self._id = id
        self._name = name
        self._color = color
        self._age = age
        self._is_molting = is_molting

    def __str__(self):
        return (f"\nChicken{{\n"
                f"id ->\t\t{self._id},\n"
                f"name ->\t{self._name},\n"
                f"color ->\t{self._color},\n"
                f"age ->\t\t{self._age},\n"
                f"isMolting ->\t{self._is_molting}}}")

    # Getters and setters (Pythonic style using properties)
    @property
    def id(self):
        return self._id

    @id.setter
    def id(self, id):
        self._id = id

    @property
    def name(self):
        return self._name

    @name.setter
    def name(self, name):
        self._name = name

    @property
    def color(self):
        return self._color

    @color.setter
    def color(self, color):
        self._color = color

    @property
    def age(self):
        return self._age

    @age.setter
    def age(self, age):
        self._age = age

    @property
    def is_molting(self):
        return self._is_molting

    @is_molting.setter
    def is_molting(self, is_molting):
        self._is_molting = is_molting