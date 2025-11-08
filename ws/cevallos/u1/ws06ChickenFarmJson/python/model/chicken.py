import random
import json
import os
from egg import Egg
from poop import Poop

class Chicken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def poop(self, amount: int) -> Poop:
        poop = Poop(amount)
        print(f"Chicken {self.name} is pooping a {poop}")
        return poop

    def lay_an_egg(self, size: str) -> Egg:
        egg = Egg(size)
        print(f"The chicken {self.name} is laying a {egg.size} size egg")
        return egg

    def cluck(self):
        print(f"The chicken {self.name} is clucking: cluck, cluck, cluck")

    def eat(self):
        print(f"The chicken {self.name} is eating grains")

    def wander(self):
        print(f"Chicken {self.name} is wandering")

    def drink(self):
        print(f"Chicken {self.name} is drinking water")

    def do_stuff(self):
        """Perform random chicken behaviors"""
        actions = [self.cluck, self.eat, self.wander, self.drink,
                   lambda: self.poop(random.randint(1, 5)),
                   lambda: self.lay_an_egg(random.choice(['S', 'M', 'L']))]
        for _ in range(random.randint(3, 6)):
            random.choice(actions)()

    def to_dict(self):
        """Convert chicken object to dictionary for JSON serialization"""
        return {
            'id': self.id,
            'name': self.name,
            'color': self.color,
            'age': self.age,
            'is_molting': self.is_molting
        }

    def save_to_json(self, filename="chickens.json"):
        """Save chicken data to a JSON file"""
        try:
            # Check if file exists and read existing data
            if os.path.exists(filename):
                with open(filename, 'r') as file:
                    data = json.load(file)
            else:
                data = []
            
            # Convert current chicken to dict
            chicken_dict = self.to_dict()
            
            # Check if chicken already exists and update, otherwise add new
            found = False
            for i, chicken in enumerate(data):
                if chicken['id'] == self.id:
                    data[i] = chicken_dict
                    found = True
                    break
            
            if not found:
                data.append(chicken_dict)
            
            # Write back to file
            with open(filename, 'w') as file:
                json.dump(data, file, indent=2)
            
            print(f"Chicken {self.name} saved to {filename}")
            
        except Exception as e:
            print(f"Error saving to JSON: {e}")

    def __str__(self):
        return (f"Chicken{{id={self.id}, name={self.name}, color={self.color}, age={self.age}, isMolting={self.is_molting}}}")