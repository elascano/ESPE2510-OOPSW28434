from egg import Egg
from poop import Poop

class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def lay_egg(self, next_egg_id):
        """Returns an Egg if not molting, otherwise None"""
        if self.is_molting:
            return None
        return Egg(next_egg_id, self.id)

    def poop(self, next_poop_id):
        """Returns a Poop object (no grams)"""
        return Poop(next_poop_id, self.id)

    def __str__(self):
        return f"id: {self.id}, name: {self.name}, color: {self.color}, age: {self.age}, isMolting: {self.is_molting}"
