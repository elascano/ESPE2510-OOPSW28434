import json
from datetime import datetime

from chickenfarmsimulator.model.egg import Egg

class Chicken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting
    
    def to_dict(self):
        return {
            'id': self.id,
            'name': self.name,
            'color': self.color,
            'age': self.age,
            'is_molting': self.is_molting
        }
    
    @classmethod
    def from_dict(cls, data):
        return cls(
            id=data['id'],
            name=data['name'],
            color=data['color'],
            age=data['age'],
            is_molting=data['is_molting']
        )
    
    def do_stuff(self, for_time: int):
        print(f"Chicken {self.name} is doing stuff for {for_time} minutes")
    
    def cluck(self):
        print(f"Chicken {self.name} is clucking: Cluck cluck cluck!")
    
    def wander(self):
        print(f"Chicken {self.name} is wandering around...")
    
    def eat(self):
        print(f"Chicken {self.name} is eating grains")
    
    def drink(self):
        print(f"Chicken {self.name} is drinking water")
    
    def poop(self):
        print(f"Chicken {self.name} is pooping")
        return "Poop"
    
    def lay_an_egg(self):
        print(f"Chicken {self.name} laid an egg!")
        return Egg(id=datetime.now().timestamp())