from Egg import Egg
from Poop import Poop

class Chicken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self.__id = id
        self.__name = name
        self.__color = color
        self.__age = age
        self.__is_molting = is_molting

    def __str__(self):
        return (f"Chicken{{\n\n  id : {self.__id}"
                f"\n  name : {self.__name}"
                f"\n  color : {self.__color}"
                f"\n  age : {self.__age}"
                f"\n  isMolting : {self.__is_molting}"
                f"\n}}")

    # --- Métodos de comportamiento ---
    def poop(self, amount: int) -> Poop:
        poop = Poop(amount)
        print(f"chicken {self.__name} is pooping a {poop}")
        return poop

    def lay_an_egg(self, size: str) -> Egg:
        egg = Egg(size)
        print(f"chicken {self.__name} is laying a {egg.get_size()} size egg")
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

    def drink(self):
        print(f"chicken {self.__name} is drinking")

    def cluck(self):
        print(f"chicken {self.__name} is clucking, cluck, cluck, cluck")

    def eat(self):
        print(f"chicken {self.__name} is eating")

    def wander(self):
        print(f"chicken {self.__name} is wandering")

    # --- Getters y Setters ---
    def get_id(self) -> int:
        return self.__id

    def set_id(self, id: int):
        self.__id = id

    def get_name(self) -> str:
        return self.__name

    def set_name(self, name: str):
        self.__name = name

    def get_color(self) -> str:
        return self.__color

    def set_color(self, color: str):
        self.__color = color

    def get_age(self) -> int:
        return self.__age

    def set_age(self, age: int):
        self.__age = age

    def is_molting(self) -> bool:
        return self.__is_molting

    def set_is_molting(self, is_molting: bool):
        self.__is_molting = is_molting
