import random
from Chiken import Chiken

def main():
    print("My Chicken Farm Simulator from kevin Chalan")

    num_chickens = int(input("How many chickens do you want on your farm? "))
    chickens = []

    colors = ["white", "black", "brown", "yellow", "spotted", "red", "black and brown"]
    egg_sizes = ['S', 'M', 'L'] 
    name_prefixes = ["Lucy", "Polly", "Henrietta", "Goldie", "Pepper", "Chick", "Roxie"]

    for i in range(1, num_chickens + 1):
        # Genera nombres aleatorios (prefijo + 3 dígitos)
        name_prefix = random.choice(name_prefixes)
        random_digits = random.randint(100, 999)
        name = f"{name_prefix}{random_digits}"

        color = random.choice(colors)
        age = random.randint(1, 5)
        is_molting = random.choice([True, False])
        chicken = Chiken(i, name, color, age, is_molting)
        chickens.append(chicken)

    for chicken in chickens:
        print("\n")
        print(f"The chiken is --->")
        print(chicken) 
        print(f"chiken id -->{chicken.id}{chicken.name}")

        chicken._eat()

        chicken._cluck()

        chicken.poop(random.randint(1, 5))

        chicken.wander()

        chicken.lay_an_egg(random.choice(egg_sizes)) 

if __name__ == "__main__":
    main()