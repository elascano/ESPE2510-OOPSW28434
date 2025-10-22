class Poop:
    def __init__(self, amount):
        self.amount = amount

    def __str__(self):
        return f"{self.amount} poop(s)"


class Egg:
    def __init__(self, size):
        self.size = size

    def get_size(self):
        return self.size


class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def __cluck(self):
        print(f"chicken {self.name} is clucking, cluck cluck cluck")

    def __eat(self):
        print(f"chicken {self.name} is eating, grains")

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

    def poop(self, amount):
        poop = Poop(amount)
        print(f"chicken {self.name} is pooping a {poop}")
        return poop

    def lay_an_egg(self, size):
        """
        Crea un huevo del tamaño indicado y lo devuelve.
        size: Tamaño del huevo (S, M, L)
        """
        egg = Egg(size)
        print(f"chicken {self.name} is laying a {egg.get_size()} size egg")
        return egg

    def wander(self):
        print(f"chicken {self.name} is wandering")

    def drink(self):
        print(f"chicken {self.name} is drinking")

    def __str__(self):
        return (f"\nChicken{{\n"
                f"id -> \t\t{self.id}, \n"
                f"name -> \t{self.name}, \n"
                f"color -> \t{self.color}, \n"
                f"age -> \t\t{self.age}, \n"
                f"isMolting -> \t{self.is_molting}\n}}")

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


if __name__ == "__main__":
    chicken = Chicken(1, "Lucy", "white", 2, False)
    print(chicken)
    chicken.do_stuff()