from poop import Poop
from egg import Egg
import random

class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def cluck(self):
        print(f" Chicken {self.name} is clucking, cluck cluck cluck!")

    def eat(self):
        print(f" Chicken {self.name} is eating grains.")

    def wander(self):
        print(f" Chicken {self.name} is wandering around.")

    def drink(self):
        print(f" Chicken {self.name} is drinking water.")

    def poop(self, amount):
        poop = Poop(amount)
        print(f" Chicken {self.name} is pooping: {poop}")
        return poop

    def lay_an_egg(self, size):
        egg = Egg(size)
        print(f" Chicken {self.name} laid a {egg.size}-size egg!")
        return egg

    def do_stuff(self):
        self.cluck()
        self.eat()
        self.cluck()

        # Valores aleatorios (random)
        poop1 = random.randint(1, 3)
        poop2 = random.randint(1, 3)
        egg_sizes = ['S', 'M', 'L']

        self.poop(poop1)
        self.poop(poop2)
        self.eat()
        self.wander()
        self.drink()
        self.lay_an_egg(random.choice(egg_sizes))
        self.lay_an_egg(random.choice(egg_sizes))

    def __str__(self):
        return (f"\nChicken{{\n"
                f"  id -> {self.id},\n"
                f"  name -> {self.name},\n"
                f"  color -> {self.color},\n"
                f"  age -> {self.age},\n"
                f"  isMolting -> {self.is_molting}\n"
                f"}}")
