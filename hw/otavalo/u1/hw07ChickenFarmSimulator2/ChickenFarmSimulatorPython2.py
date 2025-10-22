import random

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
        return f"{sizes.get(self.size, 'unknown')} egg"


class Chicken:
    id_counter = 1 

    def __init__(self, name, color, age, is_molting):
        self.id = Chicken.id_counter
        Chicken.id_counter += 1
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def cluck(self):
        print(f"Chicken {self.name} is clucking: cluck cluck cluck!")

    def eat(self):
        print(f"Chicken {self.name} is eating grains.")

    def drink(self):
        print(f"Chicken {self.name} is drinking water.")

    def wander(self):
        print(f"Chicken {self.name} is wandering around.")

    def poop(self):
        amount = random.randint(1, 5)
        poop = Poop(amount)
        print(f"Chicken {self.name} pooped → {poop}")
        return poop

    def lay_egg(self):
        size = random.choice(['S', 'M', 'L'])
        egg = Egg(size)
        print(f"Chicken {self.name} laid a {egg}")
        return egg

    def do_stuff(self):
        actions = [self.cluck, self.eat, self.drink, self.wander, self.poop, self.lay_egg]
        random.shuffle(actions)
        print(f"\n--- Chicken {self.name} is doing stuff ---")
        for action in actions[:4]:  
            action()

    def __str__(self):
        return (f"\nChicken {{\n"
                f"  id --> {self.id}\n"
                f"  name --> {self.name}\n"
                f"  color --> {self.color}\n"
                f"  age --> {self.age}\n"
                f"  isMolting --> {self.is_molting}\n}}")
    
def get_valid_string(message):
    while True:
        value = input(message).strip()
        if value.isalpha() or " " in value:
            return value
        print("Please enter a valid text (letters only).")

def get_valid_int(message):
    while True:
        value = input(message).strip()
        if value.isdigit():
            return int(value)
        print("Please enter a valid integer.")

def get_valid_bool(message):
    while True:
        value = input(message + " (true/false): ").lower().strip()
        if value in ["true", "false"]:
            return value == "true"
        print(" Please enter 'true' or 'false'.")

def create_chicken():
    print("\n Enter chicken data:")
    name = get_valid_string("Name: ")
    color = get_valid_string("Color: ")
    age = get_valid_int("Age: ")
    is_molting = get_valid_bool("Is molting")
    chicken = Chicken(name, color, age, is_molting)
    print(chicken)
    chicken.do_stuff()

def main():
    print("Welcome to my Chicken Farm Simulator")
    
    while True: 
        create_chicken()
        create_chicken()
        
        repeat = input("\nDo you want to enter 2 more chickens? (yes/no): ").lower().strip()
        if repeat != "yes":
            print("Exiting the Chicken Farm Simulator. Goodbye!")
            break

if __name__ == "__main__":
    main()
