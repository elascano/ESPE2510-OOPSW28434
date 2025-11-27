class FarmAnimal:
    def __init__(self, name, age, weight):
        self.name = name
        self.age = age
        self.weight = weight

    def eat(self):
        return f"{self.name} is eating."

    def make_sound(self):
        return "Generic animal sound."
