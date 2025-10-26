class Chicken:
    def __init__(self, id, name, color, age, isMolting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.isMolting = isMolting

    def __str__(self):
        return f"Chicken({self.id}, {self.name}, {self.color}, {self.age}, Molting={self.isMolting})"
