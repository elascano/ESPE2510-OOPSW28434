from poop import Poop
from egg import Egg

class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def __str__(self):
        return f"\nChicken{{\n id-->\t{self.id}, \n name-->\t{self.name}, \n color-->\t{self.color}, \n age-->\t{self.age}, \n isMolting-->{self.is_molting}}}"

    def __cluck(self):
        print(f"chicken {self.name} is clucking cluck cluck cluck")

    def __eat(self):
        print(f"chicken {self.name} is eating grains")

    def poop(self, amount):
        poop = Poop(amount)
        print(f"chicken {self.name} is pooping {poop}")
        return poop

    def lay_an_egg(self, size):
        egg = Egg(size)
        print(f"chicken {self.name} is laying {egg.get_size()} size egg")
        return egg

    def drink(self):
        print(f"chicken {self.name} is laying")

    def wander(self):
        print(f"chicken {self.name} is wandering")

    def do_stuff(self):
        self.__cluck()
        self.__eat()
        self.__cluck()
        self.poop(2)
        self.poop(3)
        self.__eat()
        self.wander()
        self.drink()
        self.lay_an_egg('M')
        self.lay_an_egg('L')

    def get_id(self):
        return self.id

    def set_id(self, id):
        self.id = id

    def get_name(self):
        return self.name

    def set_name(self, name):
        self.name = name

    def get_color(self):
        return self.color

    def set_color(self, color):
        self.color = color

    def get_age(self):
        return self.age

    def set_age(self, age):
        self.age = age

    def get_is_molting(self):
        return self.is_molting

    def set_is_molting(self, is_molting):
        self.is_molting = is_molting
