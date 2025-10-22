from Poop import Poop
from Egg import Egg

class Chiken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def __str__(self):
        return (f"Chiken{{\n"
                f"id---->\t\t{self.id}, \n"
                f"name---->\t\t{self.name}, \n"
                f"color-->\t\t{self.color}, \n"
                f"age---->\t\t{self.age}, \n"
                f"isMolting--->\t{self.is_molting}}}")

    def _cluck(self):
        print(f" chicken {self.name} is clucking cluck cluck cluck")

    def _eat(self):
        print(f" chicken {self.name} is eating, grains")

    def poop(self, amount: int) -> Poop:
        poop = Poop(amount)
        print(f" chicken {self.name} is pooping {poop}")
        return poop

    def lay_an_egg(self, size: str) -> Egg:
        egg = Egg(size)
        print(f" chicken {self.name} is laying ({egg.size}) size egg")
        return egg

    def drink(self):
        print(f" chicken {self.name} is drinking")

    def wander(self):
        print(f" chicken {self.name} is wandering")