class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def __str__(self):
        return f"Chicken(id={self.id}, name='{self.name}', color='{self.color}', age={self.age}, is_molting={self.is_molting})"

    def to_list(self):
        """Devuelve los datos del pollo como lista (para CSV)."""
        return [self.id, self.name, self.color, self.age, self.is_molting]

    def do_stuff(self):
        print(f"{self.name} is clucking, eating, and walking around.")
