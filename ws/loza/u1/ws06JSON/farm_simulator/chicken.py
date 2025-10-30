from egg import Egg
from poop import Poop

class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting
        self.eggs = []

    @staticmethod
    def create_from_input(id):
        print(f"\nEnter data for chicken #{id}")
        name = input("Name: ")
        color = input("Color: ")
        age = int(input("Age: "))
        is_molting = input("Is molting? (true/false): ").lower() == "true"

        chicken = Chicken(id, name, color, age, is_molting)
        num_eggs = int(input(f"How many eggs did {name} lay today? "))
        for i in range(num_eggs):
            size = input(f"Enter size for egg #{i+1} (S/M/L): ").upper()
            chicken.lay_an_egg(size)
        return chicken

    def cluck(self):
        print(f"{self.name} is clucking. Cluck cluck!")

    def eat(self):
        print(f"{self.name} is eating grains.")

    def poop(self, amount):
        poop = Poop(amount)
        print(f"{self.name} is pooping: {poop}")
        return poop

    def lay_an_egg(self, size):
        egg = Egg(size)
        self.eggs.append(egg)
        print(f"{self.name} laid a {egg.size}-sized egg!")
        return egg

    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "color": self.color,
            "age": self.age,
            "is_molting": self.is_molting,
            "eggs": [egg.to_dict() for egg in self.eggs]
        }

    @staticmethod
    def from_dict(data):
        chicken = Chicken(
            data["id"], data["name"], data["color"], data["age"], data["is_molting"]
        )
        chicken.eggs = [Egg.from_dict(e) for e in data.get("eggs", [])]
        return chicken

    def __str__(self):
        return f"Chicken(id={self.id}, name={self.name}, color={self.color}, age={self.age}, molting={self.is_molting})"
