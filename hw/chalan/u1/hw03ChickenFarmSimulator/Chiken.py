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
        return (f"\nChiken{{\nid---->\t{self.id}, "
                f"\nname---->\t{self.name}, "
                f"\ncolor-->\t{self.color}, "
                f"\nage---->\t{self.age}, "
                f"\nisMolting--->{self.is_molting}}}")

    def _cluck(self):
        print(f"Chicken {self.name} is clucking cluck cluck cluck")

    def _eat(self):
        print(f"Chicken {self.name} is eating, grains")

    def poop(self, amount: int) -> Poop:
        poop = Poop(amount)
        print(f"Chicken {self.name} is pooping {poop}")
        return poop

    def lay_an_egg(self, size: str) -> Egg:
        egg = Egg(size)
        print(f"Chicken {self.name} is laying {egg.size} size egg")
        return egg

    def drink(self):
        print(f"Chicken {self.name} is drinking")

    def wander(self):
        print(f"Chicken {self.name} is wandering")

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
