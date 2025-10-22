from datetime import date

class Chicken:
    def __init__(self, chicken_id, name, color, is_molting, age):
        self.id = chicken_id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting
    
    def cluck(self):
        print(f"chicken {self.name} is clucking, cluck, cluck , cluck")
    
    def eat(self):
        print(f"chicken {self.name} is eating, grains")