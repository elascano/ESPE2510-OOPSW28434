from .egg import Egg
from .poop import Poop
import random

class Chicken:
    def __init__(self, chicken_id, name, color, age, is_molting):
        self.id = chicken_id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    # ===== Métodos públicos =====
    def cluck(self):
        print(f"{self.name} says: ¡Cluck cluck! ")

    def eat(self):
        print(f"{self.name} is eating corn ")
        self.__digest_food()

    def poop(self):
        poop = Poop(quantity=random.randint(1, 3))
        print(f"{self.name} did {poop}")
        return poop

    def lay_egg(self):
        if self.is_molting:
            print(f"{self.name} cannot lay eggs while molting ")
            return None
        egg = Egg(size=random.choice(["small", "medium", "large"]), color=self.color)
        print(f"{self.name} laid {egg} ")
        return egg

    def do_stuff(self):
        """Simula las acciones diarias del pollo."""
        self.cluck()
        self.eat()
        if random.choice([True, False]):
            self.lay_egg()
        self.poop()

    def to_dict(self):
        """Convierte el objeto en diccionario (para JSON)."""
        return {
            "id": self.id,
            "name": self.name,
            "color": self.color,
            "age": self.age,
            "is_molting": self.is_molting
        }


    def __digest_food(self):
        print(f"{self.name} is digesting food... ")

    def __str__(self):
        return f"Chicken(id={self.id}, name='{self.name}', color='{self.color}', age={self.age}, is_molting={self.is_molting})"
