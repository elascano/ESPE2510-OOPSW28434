from dataclasses import dataclass
from typing import Optional
from .Egg import Egg
from .Poop import Poop


@dataclass
class Chicken:
    id: int
    name: str
    color: str
    age: int
    is_molting: bool

    def cluck(self) -> str:
        return f"{self.name} says: cluck!"

    def wander(self) -> str:
        return f"{self.name} is wandering around the coop."

    def eat(self) -> str:
        return f"{self.name} is eating grains."

    def drink(self) -> str:
        return f"{self.name} is drinking water."

    def poop(self) -> Poop:
        return Poop(chicken_id=self.id, weight_grams=25)

    def lay_an_egg(self) -> Optional[Egg]:
        if self.is_molting:
            return None
        return Egg(id=self.id * 1000 + self.age)

    def do_stuff(self, for_time: int) -> str:
        actions = [self.cluck(), self.wander(), self.eat(), self.drink()]
        return f"{self.name} did {len(actions)} actions in {for_time} minutes."
