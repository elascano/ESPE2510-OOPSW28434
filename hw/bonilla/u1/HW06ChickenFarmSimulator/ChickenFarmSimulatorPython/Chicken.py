class Chicken:

    def __init__(self, id, name, color, age, is_molting):

        self._id = id
        self._name = name
        self._color = color
        self._age = age
        self._its_molting = is_molting
  
    def __str__(self):
   
        return (f"Chicken{{\n"
                f"\nid ->\t\t {self._id}, \n"
                f"name ->\t\t {self._name}, \n"
                f"color ->\t {self._color}, \n"
                f"age ->\t\t {self._age}, \n"
                f"itsMolting ->\t {self._its_molting}}}")

    def get_id(self):
        return self._id

    def set_id(self, id):
        self._id = id

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

    def is_is_molting(self):
        return self._is_molting

    def set_is_molting(self, is_molting):
        self._is_molting = is_molting