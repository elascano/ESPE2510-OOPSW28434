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


for i, ch in enumerate(chickens):
    (coop1 if i < 6 else coop2).add_chicken(ch)


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

with open("farm_data.csv", "w", newline="") as f:
    writer = csv.writer(f)
    writer.writerow([
        "coopId", "coopName", "chickenId", "name", "color", "age",
        "isMolting", "eggsCount", "poopsCount"
    ])
    for coop in [coop1, coop2]:
        for c in coop.chickens:
            eggs_count = sum(1 for e in coop.eggs if e.chicken_id == c.id)
            poops_count = sum(1 for p in coop.poops if p.chicken_id == c.id)
            writer.writerow([
                coop.id, coop.name, c.id, c.name, c.color,
                c.age, c.is_molting, eggs_count, poops_count
            ])

print("\n Only one CSV created: farm_data.csv")
