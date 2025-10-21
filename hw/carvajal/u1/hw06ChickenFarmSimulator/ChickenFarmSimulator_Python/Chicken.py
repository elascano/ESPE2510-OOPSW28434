# Autor: Josue Carvajal
# Versión: 0.1

class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    # Métodos getters y setters
    def get_id(self):
        return self.id

    def set_id(self, id):
        self.id = id

    def get_name(self):
        return self.name

    def set_name(self, name):
        self.name = name

    def get_color(self):
        return self.color

    def set_color(self, color):
        self.color = color

    def get_age(self):
        return self.age

    def set_age(self, age):
        self.age = age

    def is_molting(self):
        return self.is_molting

    def set_is_molting(self, is_molting):
        self.is_molting = is_molting

    def __str__(self):
        return (f"Chicken{{\n"
                f"id -->\t\t{self.id},\n"
                f"name -->\t{self.name},\n"
                f"color -->\t{self.color},\n"
                f"age -->\t\t{self.age},\n"
                f"isMolting -->\t{self.is_molting}"
                f"}}")