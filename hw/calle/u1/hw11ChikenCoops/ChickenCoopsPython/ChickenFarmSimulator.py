from Chicken import Chicken
from ChickenCoop import ChickenCoop

print("this is my Chicken Farm Simulator by Emily Calle")

coop1 = ChickenCoop(1, "Main Coop")
coop2 = ChickenCoop(2, "Secondary Coop")

chickens = [
    Chicken(1, "Lucy", "White and Brown", 2, False),
    Chicken(2, "Matilda", "Black", 1, True),
    Chicken(3, "Henrietta", "Red", 3, False),
    Chicken(4, "Ginger", "Golden", 2, True),
    Chicken(5, "BokBok", "Spotted", 1, False),
    Chicken(6, "Clucky", "Gray", 4, False),
    Chicken(7, "Feathers", "White", 2, True),
    Chicken(8, "Sunny", "Yellow", 1, False),
    Chicken(9, "Ruby", "Dark Red", 3, False),
    Chicken(10, "Snowball", "Pure White", 2, True)
]

for i in range(6):
    coop1.add_chicken(chickens[i])

for i in range(6, 10):
    coop2.add_chicken(chickens[i])

print("\n=== CHICKEN FARM STATUS ===")
print(coop1)
print(coop2)

coop1.display_all_chickens()
coop2.display_all_chickens()

print("\n=== FARM STATISTICS ===")
print(f"Total chickens in farm: {len(chickens)}")
print(f"Chickens in {coop1.get_name()}: {coop1.get_chicken_count()}")
print(f"Chickens in {coop2.get_name()}: {coop2.get_chicken_count()}")

print("\n=== REMOVING A CHICKEN ===")
print("Before removal:")
print(f"Chickens in {coop1.get_name()}: {coop1.get_chicken_count()}")
coop1.remove_chicken(3) 
print("After removing chicken with ID 3:")
print(f"Chickens in {coop1.get_name()}: {coop1.get_chicken_count()}")

print("\n=== FINAL FARM STATUS ===")
print(coop1)
print(coop2)