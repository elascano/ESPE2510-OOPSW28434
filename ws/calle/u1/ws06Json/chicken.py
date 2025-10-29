class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting  
    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "color": self.color,
            "age": self.age,
            "is_molting": self.is_molting
        }

    def cluck(self):
        print(f"Chicken {self.name} says: Cluck cluck cluck!")