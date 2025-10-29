import os
import sys
import json

CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
ROOT_DIR = os.path.dirname(CURRENT_DIR)
if ROOT_DIR not in sys.path:
    sys.path.append(ROOT_DIR)

from model.Chicken import Chicken
from model.Chicken_Coop import ChickenCoop

DATA_FILE = os.path.join(ROOT_DIR, "farm_data.json")


def save_to_json(coops):
    data = []
    for coop in coops:
        coop_data = {"id": coop.id, "chickens": []}
        for c in coop.chickens:
            coop_data["chickens"].append({
                "id": c.id,
                "name": c.name,
                "color": c.color,
                "age": c.age,
                "is_molting": c.is_molting
            })
        data.append(coop_data)

    with open(DATA_FILE, "w", encoding="utf-8") as file:
        json.dump(data, file, indent=4)


def load_from_json():
    if not os.path.exists(DATA_FILE):
        return []

    with open(DATA_FILE, "r", encoding="utf-8") as file:
        try:
            data = json.load(file)
        except json.JSONDecodeError:
            return []

   
    if isinstance(data, dict):
        data = data.get("coops", [])

    coops = []
    for coop_data in data:
        if not isinstance(coop_data, dict):
            continue
        coop = ChickenCoop(id=coop_data.get("id", 0))
        for c_data in coop_data.get("chickens", []):
            coop.add(Chicken(
                id=c_data["id"],
                name=c_data["name"],
                color=c_data["color"],
                age=c_data["age"],
                is_molting=c_data["is_molting"]
            ))
        coops.append(coop)
    return coops


def main():
    coops = load_from_json()

    if not coops:
        coop1 = ChickenCoop(id=1)
        coop2 = ChickenCoop(id=2)
        coop1.add(Chicken(1, "Lucy", "White", 2, False))
        coop1.add(Chicken(2, "Maruja", "Brown", 1, True))
        coop2.add(Chicken(3, "Pio", "Black", 3, False))
        coops = [coop1, coop2]
        save_to_json(coops)

    while True:
        print("\n===== Chicken Coop Menu =====")
        print("1. Show all coops and chickens")
        print("2. Add a new chicken")
        print("3. Exit")
        option = input("Choose an option: ")

        if option == "1":
            for coop in coops:
                print(coop.list_chickens())

        elif option == "2":
            try:
                coop_id = int(input("Enter coop ID (1 or 2): "))
                coop = next((c for c in coops if c.id == coop_id), None)
                if not coop:
                    print("Coop not found.")
                    continue

                name = input("Enter chicken name: ")
                color = input("Enter color: ")
                age = int(input("Enter age: "))
                molting_input = input("Is it molting? (y/n): ").lower()
                is_molting = molting_input == "y"

                new_id = max((c.id for cp in coops for c in cp.chickens), default=0) + 1
                coop.add(Chicken(new_id, name, color, age, is_molting))
                save_to_json(coops)
                print(f"Chicken '{name}' added successfully to Coop {coop_id}.")
            except ValueError:
                print("⚠ Invalid input. Try again.")

        elif option == "3":
            print("Exiting Chicken Farm Simulator...")
            break

        else:
            print("Invalid option. Please choose 1, 2, or 3.")


if __name__ == "__main__":
    main()
