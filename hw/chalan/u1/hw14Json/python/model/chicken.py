from model.egg import Egg
from model.poop import Poop

class Chicken:
    def __init__(self, chicken_id, name, color, age, is_molting):
        self.chicken_id = chicken_id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting
    
    def poop(self, amount):
        poop_instance = Poop(amount)
        print(f"Chicken {self.name} is pooping a {poop_instance}")
        return poop_instance
    
    def lay_an_egg(self, size):
        egg = Egg(size)
        print(f"Chicken {self.name} is laying a {egg.get_size()} size egg")
        return egg
    
    def do_stuff(self):
        self.cluck()
        self.eat()
        self.cluck()
        self.poop(2)
        self.poop(3)
        self.eat()
        self.wander()
        self.drink()
        self.lay_an_egg('M')
        self.lay_an_egg('L')
    
    def cluck(self):
        print(f"Chicken {self.name} is clucking, cluck, cluck, cluck")
    
    def eat(self):
        print(f"Chicken {self.name} is eating")
    
    def wander(self):
        print(f"Chicken {self.name} is wandering")
    
    def drink(self):
        print(f"Chicken {self.name} is drinking")
    
    def to_dict(self):
        return {
            'chicken_id': self.chicken_id,
            'name': self.name,
            'color': self.color,
            'age': self.age,
            'is_molting': self.is_molting
        }
    
    @classmethod
    def from_dict(cls, data):
        return cls(
            data['chicken_id'],
            data['name'],
            data['color'],
            data['age'],
            data['is_molting']
        )
    
    def __str__(self):
        return f"Chicken{{id: {self.chicken_id}\t name: {self.name}\t color: {self.color}\t age: {self.age}\t isMolting: {self.is_molting}}}"