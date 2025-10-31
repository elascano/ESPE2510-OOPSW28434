import random
from .egg import Egg
from .poop import Poop

class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self._id = id
        self._name = name
        self._color = color
        self._age = age
        self._is_molting = is_molting
        self._egg_produced = 0

        self.methods_chicken = [self.cluck, self.eat, self.drink, self.poop, self.lay_an_egg, self.wander]
    
    def cluck(self):
        print(f"Chicken {self._name} is clucking")

    def eat(self):
        print(f"Chicken {self._name} is eating")

    def wander(self):
        print(f"Chicken {self._name} is wandering")

    def drink(self):
        print(f"Chicken {self._name} is drinking")

    def lay_an_egg(self):
        egg = Egg()
        self._egg_produced += 1
        print(f"Chicken {self._name} is laying an egg {egg.get_size()} size egg")
        return egg

    def poop(self):
        poop = Poop()
        print(f"Chicken {self._name} is pooping {poop}")
        return poop
    
    def do_stuff(self):
        eggs_obteined_from_this_session = 0
        for _ in range(5):
            chosen_method = random.choice(self.methods_chicken)
            if chosen_method == self.lay_an_egg:
                chosen_method()
                eggs_obteined_from_this_session += 1
            else:
                chosen_method()
        return eggs_obteined_from_this_session

    def __str__(self):
            return (f"id --> \t {self._id}\t name --> \t {self._name}\t color --> \t {self._color}\t age --> \t {self._age}\t isMolting --> \t {self._is_molting}")
    
    def get_id(self): return self._id
    def get_name(self): return self._name
    def get_color(self): return self._color
    def get_age(self): return self._age
    def get_is_molting(self): return self._is_molting
    def get_eggs_produced(self): return self._egg_produced

    def set_id(self, id): self._id = id
    def set_name(self, name): self._name = name
    def set_color(self, color): self._color = color
    def set_age(self, age): self._age = age
    def set_is_molting(self, is_molting): self._is_molting = is_molting
    def set_eggs_produced(self, eggs): self._egg_produced = eggs