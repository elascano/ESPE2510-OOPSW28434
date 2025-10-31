import random
from egg import Egg
from poop import Poop

class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self._id = id
        self._name = name
        self._color = color
        self._age = age
        self._is_molting = is_molting  # Atributo privado
    
    def do_stuff(self):
        self.cluck()
        self.eat()
        self.wander()
        self.drink()
        
        egg_sizes = ['S', 'M', 'L']
        random_size = random.choice(egg_sizes)
        self.lay_an_egg(random_size)
        
        random_amount = random.randint(1, 3)
        self.poop(random_amount)
    
    def cluck(self):
        print(f"Chicken {self._name} is clucking: cluck, cluck, cluck")
    
    def eat(self):
        print(f"Chicken {self._name} is eating grains")
    
    def wander(self):
        print(f"Chicken {self._name} is wandering around")
    
    def drink(self):
        print(f"Chicken {self._name} is drinking water")
    
    def lay_an_egg(self, size):
        egg = Egg(size)
        print(f"Chicken {self._name} laid a {size} size egg!")
        return egg
    
    def poop(self, amount):
        poop = Poop(amount)
        print(f"Chicken {self._name} pooped {amount} times")
        return poop
    
    # Getters
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
    
    # Setters
    def set_name(self, name):
        self._name = name
    
    def set_color(self, color):
        self._color = color
    
    def set_age(self, age):
        self._age = age
    
    def set_molting(self, is_molting):
        self._is_molting = is_molting
    
    def __str__(self):
        return f"Chicken{{id={self._id}, name='{self._name}', color='{self._color}', age={self._age}, isMolting={self._is_molting}}}"
    
    def __repr__(self):
        return self.__str__()