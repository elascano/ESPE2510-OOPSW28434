import random
import csv
from egg import Egg
from poop import Poop

class Chicken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def poop(self, amount: int) -> Poop:
        poop = Poop(amount)
        print(f"Chicken {self.name} is pooping a {poop}")
        return poop

    def lay_an_egg(self, size: str) -> Egg:
        egg = Egg(size)
        print(f"The chicken {self.name} is laying a {egg.size} size egg")
        return egg

    def cluck(self):
        print(f"The chicken {self.name} is clucking: cluck, cluck, cluck")

    def eat(self):
        print(f"The chicken {self.name} is eating grains")

    def wander(self):
        print(f"Chicken {self.name} is wandering")

    def drink(self):
        print(f"Chicken {self.name} is drinking water")

    def do_stuff(self):
        """Perform random chicken behaviors"""
        actions = [self.cluck, self.eat, self.wander, self.drink,
                   lambda: self.poop(random.randint(1, 5)),
                   lambda: self.lay_an_egg(random.choice(['S', 'M', 'L']))]
        for _ in range(random.randint(3, 6)):
            random.choice(actions)()

    def save_to_csv(self, filename="chickens.csv"):
        """Save chicken data to a CSV file"""
        with open(filename, mode='a', newline='') as file:
            writer = csv.writer(file)
            writer.writerow([self.id, self.name, self.color, self.age, self.is_molting])
        print(f"Chicken {self.name} saved to {filename}")

    def __str__(self):
        return (f"\nChicken{{\n"
                f"id -> {self.id},\n"
                f"name -> {self.name},\n"
                f"color -> {self.color},\n"
                f"age -> {self.age},\n"
                f"isMolting -> {self.is_molting}\n}}")
