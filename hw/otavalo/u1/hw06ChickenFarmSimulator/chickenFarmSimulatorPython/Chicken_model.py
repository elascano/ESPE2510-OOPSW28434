class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def __str__(self):
        return (f"Chicken {{\n"
                f"  id        --> {self.id}\n"
                f"  name      --> {self.name}\n"
                f"  color     --> {self.color}\n"
                f"  age       --> {self.age}\n"
                f"  isMolting --> {self.is_molting}\n"
                f"}}")

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

    def is_is_molting(self):
        return self.is_molting

    def set_is_molting(self, is_molting):
        self.is_molting = is_molting
