class Chicken:
    def __init__(self, id, name, color, age, molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = int(age)
        self.molting = molting

    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "color": self.color,
            "age": self.age,
            "molting": self.molting
        }
