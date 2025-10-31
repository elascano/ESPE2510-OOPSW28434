import os
import sys
import json

#@author: Josue Rojas
CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
ROOT_DIR = os.path.dirname(CURRENT_DIR)
if ROOT_DIR not in sys.path:
    sys.path.append(ROOT_DIR)

from model.Chicken import Chicken
from model.Chicken_Coop import ChickenCoop

DATA_FILE = os.path.join(ROOT_DIR, "farm_data.json")
def save_to_json(coops):
    """Save coop data into JSON file."""
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
    """Load coops from JSON; accepts either dict {'coops': [...]} or list."""
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
        for c in coop_data.get("chickens", []):
            coop.add(Chicken(
                id=c["id"],
                name=c["name"],
                color=c["color"],
                age=c["age"],
                is_molting=c["is_molting"]
            ))
        coops.append(coop)
    return coops


def next_chicken_id(coops):
    return max((c.id for cp in coops for c in cp.chickens), default=0) + 1

def find_coop(coops, coop_id):
    return next((cp for cp in coops if cp.id == coop_id), None)

def find_chicken(coops, chicken_id):
    for coop in coops:
        for idx, ch in enumerate(coop.chickens):
            if ch.id == chicken_id:
                return coop, ch, idx
    return None, None, -1

def coop_id_exists(coops, coop_id):
    return any(cp.id == coop_id for cp in coops)


def show_all(coops):
    print("\n==============================")
    print("       FARM INFORMATION       ")
    print("==============================")
    if not coops:
        print("No chicken coops found.")
        return
    for coop in coops:
        print(f"\nCOOP #{coop.id}")
        print("------------------------------------------------------------")
        print(f"{'ID':<5} {'NAME':<15} {'COLOR':<12} {'AGE':<5} {'MOLTING':<10}")
        print("------------------------------------------------------------")
        if not coop.chickens:
            print("(No chickens in this coop)")
        else:
            for c in sorted(coop.chickens, key=lambda x: x.id):
                print(f"{c.id:<5} {c.name:<15} {c.color:<12} {c.age:<5} {str(c.is_molting):<10}")
        print("------------------------------------------------------------")


def add_coop_flow(coops):
    try:
        print("\n=== ADD NEW COOP ===")
        coop_id = int(input("Enter new coop ID: ").strip())
        if coop_id_exists(coops, coop_id):
            print("A coop with that ID already exists.")
            return
        coops.append(ChickenCoop(id=coop_id))
        save_to_json(coops)
        print(f"Coop #{coop_id} created.")
    except ValueError:
        print("Invalid input. Try again.")

def add_chicken_flow(coops):
    try:
        print("\n=== ADD NEW CHICKEN ===")
        coop_id = int(input("Enter coop ID: ").strip())
        coop = find_coop(coops, coop_id)
        if not coop:
            print("Coop not found.")
            return
        name = input("Enter chicken name: ").strip()
        color = input("Enter color: ").strip()
        age = int(input("Enter age: ").strip())
        is_molting = input("Is it molting? (y/n): ").strip().lower() == "y"

        new_id = next_chicken_id(coops)
        coop.add(Chicken(new_id, name, color, age, is_molting))
        save_to_json(coops)
        print(f"Chicken '{name}' added to Coop #{coop_id} with ID {new_id}.")
    except ValueError:
        print("Invalid input. Try again.")

def remove_chicken_flow(coops):
    try:
        print("\n=== REMOVE CHICKEN ===")
        chicken_id = int(input("Enter chicken ID to remove: ").strip())
        coop, ch, idx = find_chicken(coops, chicken_id)
        if not ch:
            print("Chicken not found.")
            return
        del coop.chickens[idx]
        save_to_json(coops)
        print(f"Chicken ID {chicken_id} removed from Coop #{coop.id}.")
    except ValueError:
        print("Invalid input. Try again.")

def edit_chicken_flow(coops):
    try:
        print("\n=== EDIT CHICKEN ===")
        chicken_id = int(input("Enter chicken ID to edit: ").strip())
        coop, ch, idx = find_chicken(coops, chicken_id)
        if not ch:
            print("Chicken not found.")
            return

        print(f"\nEditing Chicken (ID={ch.id})")
        print(f"Current: Name={ch.name}, Color={ch.color}, Age={ch.age}, Molting={ch.is_molting}")
        print("\nSelect field to edit:")
        print("1. Name")
        print("2. Color")
        print("3. Age")
        print("4. Molting status")
        print("5. Move to another coop")
        print("6. Cancel")
        choice = input("Option: ").strip()

        if choice == "1":
            ch.name = input("New name: ").strip()
        elif choice == "2":
            ch.color = input("New color: ").strip()
        elif choice == "3":
            ch.age = int(input("New age: ").strip())
        elif choice == "4":
            ch.is_molting = input("Is it molting? (y/n): ").strip().lower() == "y"
        elif choice == "5":
            new_coop_id = int(input("Enter target coop ID: ").strip())
            target = find_coop(coops, new_coop_id)
            if not target:
                print("Target coop not found.")
                return
            del coop.chickens[idx]
            target.add(ch)
            print(f"Chicken moved to Coop #{new_coop_id}.")
        elif choice == "6":
            print("Edit cancelled.")
            return
        else:
            print("Invalid option.")
            return

        save_to_json(coops)
        print("Changes saved.")
    except ValueError:
        print("Invalid input. Try again.")



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
        print("\n==============================")
        print("        CHICKEN FARM MENU     ")
        print("==============================")
        print("1. View all coops and chickens")
        print("2. Add coop")
        print("3. Add chicken")
        print("4. Remove chicken")
        print("5. Edit chicken")
        print("6. Exit")
        option = input("Choose an option: ").strip()

        if option == "1":
            show_all(coops)
        elif option == "2":
            add_coop_flow(coops)
        elif option == "3":
            add_chicken_flow(coops)
        elif option == "4":
            remove_chicken_flow(coops)
        elif option == "5":
            edit_chicken_flow(coops)
        elif option == "6":
            print("Exiting Chicken Farm Simulator.")
            break
        else:
            print("Invalid option. Please choose 1–6.")


if __name__ == "__main__":
    main()
