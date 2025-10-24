# ============================================
# Chicken Farm Simulator
# Author: Josue Rojas
# Owner: Josue Rojas
# ============================================

import random
import time

class Poop:
    def __init__(self, amount):
        self.amount = amount
    def __str__(self):
        return "Poop size: big" if self.amount > 3 else "Poop size: small"

class Egg:
    def __init__(self, size):
        self.size = size
    def __str__(self):
        sizes = {'S': 'small', 'M': 'medium', 'L': 'large'}
        return "{} egg".format(sizes.get(self.size, 'unknown'))

class Chicken:
    def __init__(self, id_, name, color, age, is_molting):
        self.id = id_
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def cluck(self):  print("Chicken {} is clucking.".format(self.name))
    def eat(self):    print("Chicken {} is eating grains.".format(self.name))
    def drink(self):  print("Chicken {} is drinking water.".format(self.name))
    def wander(self): print("Chicken {} is wandering around.".format(self.name))
    def poop(self):
        amount = random.randint(1, 5)
        print("Chicken {} is pooping amount --> {}".format(self.name, amount))
        return Poop(amount)
    def lay_egg(self):
        size = random.choice(['S', 'M', 'L'])
        egg = Egg(size)
        print("Chicken {} laid a {}".format(self.name, egg))
        return egg

    def do_random_actions(self):
        actions = [self.cluck, self.eat, self.drink, self.wander, self.poop, self.lay_egg]
        num_actions = random.randint(3, 6)
        print("\n--- Chicken {} is about to perform {} random actions ---".format(self.name, num_actions))
        random.shuffle(actions)
        for i in range(num_actions):
            ask = input("Do you want to know what the chicken is doing? (y/n): ").strip().lower()
            if ask in ("y", "yes"):
                actions[i]()
            else:
                print("You chose not to see what {} is doing right now.".format(self.name))
            time.sleep(0.4)

    def __str__(self):
        return ("\nChicken:\n"
                "id -->\t\t{}\n"
                "name -->\t{}\n"
                "color -->\t{}\n"
                "age -->\t\t{}\n"
                "isMolting -->\t{}\n".format(self.id, self.name, self.color, self.age, self.is_molting))


def create_chicken(id_):
    print("\nNow is your moment to register a new chicken")
    name = input("Enter chicken name: ").strip() or "NoName"
    color = input("Enter chicken color: ").strip() or "Brown"
    try:
        age = int(input("Enter chicken age: ").strip())
    except:
        age = 1
    molting_input = input("Is the chicken molting? (yes/no): ").strip().lower()
    is_molting = molting_input in ("yes", "y")
    c = Chicken(id_, name, color, age, is_molting)
    print("\nYou have registered a new chicken:")
    print(c)
    return c


def main():
    print("Welcome to the Chicken Farm Simulator by Josue Rojas\n")

    # initial chickens
    chickens = [
        Chicken(1, "Lucy", "White", 2, False),
        Chicken(2, "Maruja", "Brown", 3, True)
    ]

    for c in chickens:
        print(c)
        c.do_random_actions()

    next_id = 3
    current_index = 0

    while True:
        choice = input("\nDo you want to see another chicken or add a new one? (see/add/exit): ").strip().lower()
        if choice in ("see", "s"):
            current_index += 1
            if current_index >= len(chickens):
                print("There are no more chickens to see.")
                continue
            chicken = chickens[current_index]
            print(chicken)
            chicken.do_random_actions()
        elif choice in ("add", "a"):
            new_chicken = create_chicken(next_id)
            next_id += 1
            new_chicken.do_random_actions()
            chickens.append(new_chicken)
        elif choice in ("exit", "no", "n"):
            print("\nSimulation finished. Goodbye, farmer!")
            break
        else:
            print("Please choose a valid option: see / add / exit.")


if __name__ == "__main__":
    main()
