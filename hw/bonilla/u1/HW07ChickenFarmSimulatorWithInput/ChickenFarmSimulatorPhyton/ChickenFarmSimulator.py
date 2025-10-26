class Poop:
    def __init__(self, amount):
        self.amount = amount
    def __str__(self):
        return f"Poop{{amount={self.amount}}}"
    def get_amount(self):
        return self.amount
    def set_amount(self, amount):
        self.amount = amount

class Egg:
    def __init__(self, size):
        self.size = size
    def __str__(self):
        return f"Egg{{size={self.size}}}"
    def get_size(self):
        return self.size
    def set_size(self, size):
        self.size = size

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

def main():
    print("---Chicken Farm---")
    chicken1 = Chicken(1, "Lucy", "White and Brown", 2, False)
    chicken2 = Chicken(2, "Maruja", "White", 1, True)
    chicken3 = Chicken(3, "Carmen", "Golden", 3, False)
    chickens = [chicken1, chicken2, chicken3]

    while True:
        print("\n1. Show the chickens's characteristic")
        print("2. Show the chicken's routine")
        print("3. Exit")
        choice = input("Choose an option: ")
        if choice == "1":
            for chicken in chickens:
                print(chicken)
        elif choice == "2":
            while True:
                print("\nWhich chicken's routine do you want to see?")
                print("1. Lucy")
                print("2. Maruja")
                print("3. Carmen")
                sub_choice = input("Choose an option: ")
                if sub_choice in ["1", "2", "3"]:
                    chickens[int(sub_choice) - 1].do_stuff()
                    break
                else:
                    print("Invalid choice, please select 1, 2, or 3.")
        elif choice == "3":
            break
        else:
            print("Invalid choice, please select 1, 2, or 3.")

if __name__ == "__main__":
    main()
