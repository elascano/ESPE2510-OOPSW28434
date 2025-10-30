class Chicken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def cluck(self):
        print(f"{self.name} is clucking.")

    def eat(self):
        print(f"{self.name} is eating.")

    def poop(self):
        print(f"{self.name} is pooping.")

    def lay_an_egg(self):
        print(f"{self.name} laid an egg.")

    def to_dict(self):
        return self.__dict__