from Egg import Egg
from Poop import Poop

class Chicken:
    def __init__(self, chicken_id, name, color, is_molting, age):
        self.id = chicken_id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def cluck(self):
        print(f"Chicken {self.name} says: Cluck cluck!")

    def eat(self):
        print(f"Chicken {self.name} is eating grains.")

    def lay_egg(self):
        size = input("Enter egg size (small, medium, large): ")
        return self._make_egg(size)

    def poop(self):
        amount = input("Enter poop amount (low, medium, high): ")
        return self._make_poop(amount)

    def _make_egg(self, size): 
        """Internal logic for creating an Egg object."""
        egg = Egg(size)
        print(f"Chicken {self.name} laid {egg}.")
        return egg

    def _make_poop(self, amount):
        """Internal logic for creating a Poop object."""
        poop = Poop(amount)
        print(f"Chicken {self.name} produced {poop}.")
        return poop

    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "color": self.color,
            "is_molting": self.is_molting,
            "age": self.age
        }