import sys
import os
sys.path.append(os.path.join(os.path.dirname(__file__), '..'))

from model.Chicken import Chicken
from model.ChickenCoop import ChickenCoop

chicken_coops = [
    ChickenCoop(1, "Main Coop"),
    ChickenCoop(2, "Secondary Coop")
]

chickens = [
    Chicken(1, "Lucy", "White", 2, True),
    Chicken(2, "Maruja", "Brown", 1, False),
    Chicken(3, "Cluckencia", "Speckled", 4, True),
    Chicken(4, "Piolina", "Black", 2, False),
    Chicken(5, "Plumifera", "Gray", 3, True),
    Chicken(6, "Cascaronia", "Brown", 1, False),
    Chicken(7, "Ponederas", "White", 2, True),
    Chicken(8, "Picotera", "Golden", 4, False),
    Chicken(9, "Alitona", "Red", 1, True),
    Chicken(10, "Copetona", "Spotted", 3, False)
]

# Asignar gallinas a los gallineros
for chicken in chickens[:5]:
    chicken_coops[0].add_chicken(chicken)
for chicken in chickens[5:]:
    chicken_coops[1].add_chicken(chicken)

# Mostrar información del primer gallinero
print(f" Main Coop (ID: {chicken_coops[0].id})")
print(f"Total chickens: {len(chicken_coops[0].get_chickens())}")
print("-" * 60)
print(f"{'ID':<3} {'Name':<12} {'Color':<12} {'Age':<4} {'Molting'}")
print("-" * 60)
for chicken in chicken_coops[0].get_chickens():
    print(f"{chicken.id:<3} {chicken.name:<12} {chicken.color:<12} {chicken.age:<4} {'Yes' if chicken.is_molting else 'No'}")

print("\n" + "=" * 60)

# Mostrar información del segundo gallinero
print(f"\n Secondary Coop (ID: {chicken_coops[1].id})")
print(f"Total chickens: {len(chicken_coops[1].get_chickens())}")
print("-" * 60)
print(f"{'ID':<3} {'Name':<12} {'Color':<12} {'Age':<4} {'Molting'}")
print("-" * 60)
for chicken in chicken_coops[1].get_chickens():
    print(f"{chicken.id:<3} {chicken.name:<12} {chicken.color:<12} {chicken.age:<4} {'Yes' if chicken.is_molting else 'No'}")

print("\n" + "=" * 60)
print("\nFarm Owner: Emily Calle")
print("=== Farm Information Display Completed ===")