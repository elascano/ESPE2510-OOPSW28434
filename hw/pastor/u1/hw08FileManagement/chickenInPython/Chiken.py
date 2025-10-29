from Egg import Egg
from Poop import Poop
import random

class Chicken:
    def __init__(self, id, name, color, age, isMolting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.isMolting = isMolting

    def cluck(self):
        print("chicken " + self.name + " is clucking")

    def eat(self):
        print("chicken " + self.name + " is eating")
    
    def wander(self):
        print("chicken " + self.name + " is wandering")
    
    def drink(self):
        print("chicken " + self.name + " is drinking")

    def poop(self):
        poop = Poop()
        print("chicken " + self.name + " is pooping " + str(poop))
        return poop

    def layAnEgg(self):
        egg = Egg()
        print("chicken " + self.name + " is laying an egg " + str(egg) + " size egg")
        return egg

    methods_chicken = [cluck, eat, wander, drink, poop, layAnEgg]

    def doStuff(self):
        for _ in range(5):    
            choose_method = random.choice(self.methods_chicken)
            choose_method(self)
    
    def __str__(self):
        return f"[id --> \t\t{self.id} \t\tname --> \t{self.name} \t\tcolor --> \t{self.color} \t\t\tage --> \t{self.age} \t\tisMolting --> \t\t{self.isMolting}]"

    def id(self):
        return self._id
    
    def id(self, id):
        self._id = id

    def name(self):
        return self._name   
    
    def name(self, name):
        self._name = name   
    
    def color(self):
        return self._color
    
    def color(self, color):
        self._color = color 
    
    def age(self):
        return self._age   
    
    def age(self, age):
        self._age = age 
    
    def isMolting(self):
        return self._isMolting
    
    def isMolting(self, isMolting):
        self._isMolting = isMolting