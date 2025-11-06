import os
import json
import sys
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from model.ChickenCoop import ChickenCoop
from model.Chicken import Chicken

# Obtener la ruta del directorio actual (view)
BASE_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))  # Subimos un nivel desde view
DATA_DIR = os.path.join(BASE_DIR, "data")

# Crear carpeta "data" si no existe
if not os.path.exists(DATA_DIR):
    os.makedirs(DATA_DIR)

# Ruta completa del archivo JSON
DATA_FILE = os.path.join(DATA_DIR, "chicken_farm.json")

# Solo dos gallineros disponibles
coops = [ChickenCoop(1), ChickenCoop(2)]


# === Funciones de apoyo ===
def load_data():
    global coops
    if os.path.exists(DATA_FILE):
        with open(DATA_FILE, "r", encoding="utf-8") as file:
            data = json.load(file)
        coops = [ChickenCoop(1), ChickenCoop(2)]
        for coop_data in data["coops"]:
            coop = next((c for c in coops if c.id == coop_data["id"]), None)
            if coop:
                for ch in coop_data["chickens"]:
                    chicken = Chicken(ch["id"], ch["name"], ch["color"], ch["age"], ch["molting"])
                    coop.add_chicken(chicken)


def save_data():
    data = {"coops": []}
    for coop in coops:
        coop_data = {
            "id": coop.id,
            "chickens": [
                {
                    "id": c.get_id(),
                    "name": c.get_name(),
                    "color": c.get_color(),
                    "age": c.get_age(),
                    "molting": c.is_molting()
                }
                for c in coop.chickens
            ]
        }
        data["coops"].append(coop_data)
    with open(DATA_FILE, "w", encoding="utf-8") as file:
        json.dump(data, file, indent=2)


# === CRUD ===
def insert_chicken():
    load_data()
    print("Available coops: 1 and 2")
    coop_id = int(input("Enter coop ID (1 or 2): "))
    coop = next((c for c in coops if c.id == coop_id), None)
    if not coop:
        print("Invalid coop ID.")
        return

    next_id = max([c.get_id() for c in coop.chickens], default=0) + 1
    name = input("Name: ")
    color = input("Color: ")
    age = int(input("Age: "))
    molting = input("Is molting? (y/n): ").lower() == "y"

    chicken = Chicken(next_id, name, color, age, molting)
    coop.add_chicken(chicken)
    save_data()
    print(f" Chicken '{name}' added to coop {coop.id} with ID {next_id}")


def list_chickens():
    load_data()
    for coop in coops:
        print(f"\n Coop {coop.id}:")
        if not coop.chickens:
            print("\tNo chickens.")
            continue

        print("\nID\tName\tColor\tAge\tMolting")
        print("---------------------------------------------------")
        for c in coop.chickens:
            print(f"{c.get_id()}\t{c.get_name()}\t{c.get_color()}\t{c.get_age()}\t{'Yes' if c.is_molting() else 'No'}")
        print("---------------------------------------------------\n")


def delete_chicken():
    load_data()
    coop_id = int(input("Enter coop ID (1 or 2): "))
    coop = next((c for c in coops if c.id == coop_id), None)
    if not coop or not coop.chickens:
        print("No chickens in this coop.")
        return

    id_to_delete = int(input("Enter chicken ID to delete: "))
    for i, c in enumerate(coop.chickens):
        if c.get_id() == id_to_delete:
            coop.chickens.pop(i)
            save_data()
            print(f"🗑️ Chicken {id_to_delete} deleted.")
            return
    print("Chicken not found.")


def update_chicken():
    load_data()
    coop_id = int(input("Enter coop ID (1 or 2): "))
    coop = next((c for c in coops if c.id == coop_id), None)
    if not coop or not coop.chickens:
        print("No chickens found.")
        return

    id_to_update = int(input("Enter chicken ID to update: "))
    chicken = next((c for c in coop.chickens if c.get_id() == id_to_update), None)
    if chicken:
        name = input(f"New name ({chicken.get_name()}): ") or chicken.get_name()
        color = input(f"New color ({chicken.get_color()}): ") or chicken.get_color()
        age_input = input(f"New age ({chicken.get_age()}): ")
        age = int(age_input) if age_input else chicken.get_age()
        molting = input("Is molting? (y/n): ").lower() == "y"

        chicken.set_name(name)
        chicken.set_color(color)
        chicken.set_age(age)
        chicken.set_is_molting(molting)

        save_data()
        print(" Chicken updated successfully.")
    else:
        print("Chicken not found.")


def find_chicken():
    load_data()
    id_to_find = int(input("Enter chicken ID to find: "))
    found = False

    for coop in coops:
        chicken = next((c for c in coop.chickens if c.get_id() == id_to_find), None)
        if chicken:
            print(f"\n Found in coop {coop.id}:")
            print("\nID\tName\tColor\tAge\tMolting")
            print("---------------------------------------------------")
            print(f"{chicken.get_id()}\t{chicken.get_name()}\t{chicken.get_color()}\t{chicken.get_age()}\t{'Yes' if chicken.is_molting() else 'No'}")
            print("---------------------------------------------------\n")
            found = True

    if not found:
        print("Chicken not found.")


# === Programa principal ===
def main():
    print("--- Chicken Farm Simulator ---\n")
    while True:
        print("\n===== MENU =====")
        print("1. Insert chicken")
        print("2. List chickens")
        print("3. Delete chicken")
        print("4. Update chicken")
        print("5. Find chicken")
        print("6. Exit")

        option = input("Choose an option: ")
        if option == "1":
            insert_chicken()
        elif option == "2":
            list_chickens()
        elif option == "3":
            delete_chicken()
        elif option == "4":
            update_chicken()
        elif option == "5":
            find_chicken()
        elif option == "6":
            print("Exiting simulator...")
            break
        else:
            print("Invalid option.")


if __name__ == "__main__":
    main()
