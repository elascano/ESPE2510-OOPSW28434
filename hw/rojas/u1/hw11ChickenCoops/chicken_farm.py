# chicken_farm.py
# Owner/Author: Josue Rojas

from chicken import Chicken
from chicken_coop import ChickenCoop
import csv
import random

print("===  Chicken Farm Simulator (Python) ===")


coop1 = ChickenCoop(1, "North Coop")
coop2 = ChickenCoop(2, "South Coop")

chickens = [
    Chicken(1, "Lucy", "White", 2, False),
    Chicken(2, "Maruja", "Brown", 1, True),
    Chicken(3, "Coco", "Black", 3, False),
    Chicken(4, "Bella", "Yellow", 1, False),
    Chicken(5, "Lola", "Gray", 2, True),
    Chicken(6, "Nina", "White", 4, False),
    Chicken(7, "Kira", "Brown", 2, False),
    Chicken(8, "Daisy", "White", 1, False),
    Chicken(9, "Molly", "Black", 3, True),
    Chicken(10, "Gigi", "White", 2, False),
]

for i, chicken01 in enumerate(chickens):
    (coop1 if i < 6 else coop2).add_chicken(chicken01)

egg_id, poop_id = 1, 1
for coop in [coop1, coop2]:
    for ch in coop.chickens:
        if not ch.is_molting and random.random() < 0.6:
            coop.collect_egg(ch.lay_egg(egg_id))
            egg_id += 1
        coop.collect_poop(ch.poop(poop_id))
        poop_id += 1

coop1.show_info()
coop2.show_info()

with open("chickens.csv", "w", newline="") as f:
    writer = csv.writer(f)
    writer.writerow(["coopId", "coopName", "chickenId", "name", "color", "age", "isMolting"])
    for coop in [coop1, coop2]:
        for c in coop.chickens:
            writer.writerow([coop.id, coop.name, c.id, c.name, c.color, c.age, c.is_molting])

print("\n CSV file created: chickens.csv")
