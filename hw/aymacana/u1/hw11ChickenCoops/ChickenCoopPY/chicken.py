import random
from egg import Egg
from poop import Poop

class Chicken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self._id = id
        self._name = name
        self._color = color
        self._age = age
        self._is_molting = is_molting

    def __clock(self):
        print(f"Chicken {self._name} is clucking, cluck cluck cluck")

    def __eat(self):
        print(f"Chicken {self._name} is eating granis")
    
    def do_stuff(self):
        self.__clock()
        self.__eat()
        random_poop_amount = random.randint(1, 5)
        self.poop(random_poop_amount)
        self.wonder()
        self.drink()
        egg_sizes = ['S', 'M', 'L']
        random_egg_size = random.choice(egg_sizes)
        self.lay_an_egg(random_egg_size)

    def poop(self, amount):
        poop = Poop(amount)
        print(f"Chicken {self._name} is pooping a {poop}")
        return poop
    
    def lay_an_egg(self, size):
        egg = Egg(size)
        print(f"Chicken {self.name} is laying a {egg.size}-sized egg")
        return egg
    
    def wonder(self):
        print(f"Chicken {self.name} is wandering")

    def drink(self):
        print(f"Chicken {self.name} is drinking")

    def __str__(self):
        return (f"\n{self._name}:{{\n"
                f"id \t\t->\t{self._id}\n"
                f"name \t\t->\t{self._name}\n"
                f"color \t\t->\t{self._color}\n"
                f"age \t\t->\t{self._age}\n"
                f"isMolting \t->\t{self._is_molting}}}")

    @property
    def id(self):
        return self._id

    @id.setter
    def id(self, id):
        self._id = id

    @property
    def name(self):
        return self._name

    @name.setter
    def name(self, name):
        self._name = name

    @property
    def color(self):
        return self._color

    @color.setter
    def color(self, color):
        self._color = color

    @property
    def age(self):
        return self._age

    @age.setter
    def age(self, age):
        self._age = age

    @property
    def is_molting(self):
        return self._is_molting

    @is_molting.setter
    def is_molting(self, is_molting):
        self._is_molting = is_molting