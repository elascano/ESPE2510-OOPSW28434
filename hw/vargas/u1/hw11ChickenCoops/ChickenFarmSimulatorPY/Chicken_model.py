from Poop_model import Poop
from Egg_model import Egg

class Chicken:
    def __init__(self, id, name, color, age, is_molting):

        self._id = id
        self._name = name
        self._color = color
        self._age = age
        self._is_molting = is_molting

    def _cluck(self):
        print(f"chicken {self._name} is clucking, cluck cluck cluck")

    def _eat(self):
        print(f"chicken {self._name} is eating, grains")


    def do_stuff(self):

        self._cluck()
        self._eat()
        self._cluck()
        self.poop(2)
        self.poop(3)
        self._eat()
        self.wander()
        self.drink()
        self.lay_an_egg('M')
        self.lay_an_egg('L')
        
    def poop(self, amount):

        poop_instance = Poop(amount)
        print(f"chicken {self._name} is pooping a {poop_instance}")
        return poop_instance
    
    def lay_an_egg(self, size):

        egg_instance = Egg(size)
        print(f"chicken {self._name} is laying a {egg_instance.get_size()} size egg")
        return egg_instance

    def wander(self):
        print(f"chicken {self._name} is wandering")

    def drink(self):
        print(f"chicken {self._name} is drinking")

    def __str__(self):
        return (
            f"\nChicken{{"
            f"\tid -> {self._id}, "
            f"\tname -> {self._name}, "
            f"\tcolor -> {self._color}, "
            f"\tage -> {self._age}, "
            f"\tisMolting -> {self._is_molting}"
            f"}}"
        )

    def get_id(self):
        return self._id

    def get_name(self):
        return self._name

    def get_color(self):
        return self._color

    def get_age(self):
        return self._age

    def is_molting(self):
        
        return self._is_molting

    
    def set_id(self, id):
        self._id = id

    def set_name(self, name):
        self._name = name

    def set_color(self, color):
        self._color = color

    def set_age(self, age):
        self._age = age

    def set_is_molting(self, is_molting):
        self._is_molting = is_molting