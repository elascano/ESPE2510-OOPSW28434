import random
from ChickenFarmSimulator_model.Egg import Egg
from ChickenFarmSimulator_model.Poop import Poop

class Chicken:
    def __init__(self, id, name, color, is_molting=False):
        self.id = id
        self.name = name
        self.color = color
        self.is_molting = is_molting

    def show_data(self):
        print(f"\nname -> {self.name}")
        print(f"color -> {self.color}")
        print(f"isMolting -> {self.is_molting}")

    def do_routine(self):
        print(f"\nChicken {self.name} is clucking, cluck cluck cluck")
        print(f"Chicken {self.name} is eating grains")
        poop = Poop()
        print(f"Chicken {self.name} is pooping -> {poop}")
        print(f"Chicken {self.name} is walking")
        print(f"Chicken {self.name} is drinking")
        egg = Egg()
        print(f"Chicken {self.name} is laying a {egg}")