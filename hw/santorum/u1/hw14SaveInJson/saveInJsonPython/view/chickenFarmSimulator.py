import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), "..")))

import json
from model.Coop import Coop
from model.Chicken import Chicken

DATA_FILE = "data.json"
coops = []


# === FUNCIONES AUXILIARES ===
def save_data():
    with open(DATA_FILE, "w") as f:
        json.dump([c.to_dict() for c in coops], f, indent=2)
    print("Data saved!\n")


def load_data():
    global coops
    if os.path.exists(DATA_FILE):
        with open(DATA_FILE, "r") as f:
            data = json.load(f)
            coops = []
            for coop_data in data:
                coop = Coop(coop_data["id"])
                for ch in coop_data["chickens"]:
                    coop.add_chicken(Chicken(**ch))
                coops.append(coop)
    else:
        coops = []


def print_table(data):
    if not data:
        print("No chickens available.\n")
        return

    headers = ["ID", "Name", "Color", "Age", "Molting"]
    col_widths = [len(h) for h in headers]

    for row in data:
        for i, key in enumerate(["id", "name", "color", "age", "molting"]):
            col_widths[i] = max(col_widths[i], len(str(getattr(row, key))))

    def format_row(values):
        return " | ".join(str(val).ljust(col_widths[i]) for i, val in enumerate(values))

    print(format_row(headers))
    print("-+-".join("-" * w for w in col_widths))
    for ch in data:
        print(format_row([ch.id, ch.name, ch.color, ch.age, "Yes" if ch.molting else "No"]))
    print()


# === FUNCIONES PRINCIPALES ===
def add_coop():
    coop_id = input("Enter coop ID: ")
    if any(c.id == coop_id for c in coops):
        print("A coop with that ID already exists.\n")
        return
    coops.append(Coop(coop_id))
    save_data()


def add_chicken():
    if not coops:
        print("You must create a coop first.\n")
        return

    print("Available coops:")
    for c in coops:
        print(f"- Coop {c.id}")

    coop_id = input("Enter coop ID where to add the chicken: ")
    coop = next((c for c in coops if c.id == coop_id), None)
    if not coop:
        print("Coop not found.\n")
        return

    id_ = input("Enter chicken ID: ")
    if any(ch.id == id_ for ch in coop.chickens):
        print("A chicken with that ID already exists.\n")
        return

    name = input("Enter name: ")
    color = input("Enter color: ")
    age = input("Enter age: ")
    molting = input("Is molting? (y/n): ").lower() == "y"

    coop.add_chicken(Chicken(id_, name, color, age, molting))
    save_data()
    print("Chicken added!\n")


def view_chickens():
    if not coops:
        print("No coops available.\n")
        return

    for coop in coops:
        print(f"\n=== Coop {coop.id} ===")
        print_table(coop.chickens)


def edit_chicken():
    coop_id = input("Enter coop ID: ")
    coop = next((c for c in coops if c.id == coop_id), None)
    if not coop:
        print("Coop not found.\n")
        return

    id_ = input("Enter chicken ID to edit: ")
    chicken = next((ch for ch in coop.chickens if ch.id == id_), None)
    if not chicken:
        print("Chicken not found.\n")
        return

    name = input(f"Name ({chicken.name}): ") or chicken.name
    color = input(f"Color ({chicken.color}): ") or chicken.color
    age = input(f"Age ({chicken.age}): ") or chicken.age
    molting_input = input("Molting (y/n): ").lower()
    molting = chicken.molting if molting_input == "" else molting_input == "y"

    chicken.name = name
    chicken.color = color
    chicken.age = age
    chicken.molting = molting
    save_data()
    print("Chicken updated!\n")


def delete_chicken():
    coop_id = input("Enter coop ID: ")
    coop = next((c for c in coops if c.id == coop_id), None)
    if not coop:
        print("Coop not found.\n")
        return

    id_ = input("Enter chicken ID to delete: ")
    chicken = next((ch for ch in coop.chickens if ch.id == id_), None)
    if not chicken:
        print("Chicken not found.\n")
        return

    coop.chickens.remove(chicken)
    save_data()
    print(f"Chicken '{chicken.name}' deleted!\n")


def find_chicken():
    id_ = input("Enter chicken ID to find: ")
    found = False
    for coop in coops:
        chicken = next((ch for ch in coop.chickens if ch.id == id_), None)
        if chicken:
            print(f"\nFound in Coop {coop.id}:")
            print_table([chicken])
            found = True
    if not found:
        print("Chicken not found.\n")


# === MENÚ PRINCIPAL ===
def main_menu():
    while True:
        print("=== Chicken Farm Simulator ===")
        print("1. Add coop")
        print("2. Add chicken")
        print("3. View chickens")
        print("4. Edit chicken")
        print("5. Delete chicken")
        print("6. Find chicken")
        print("0. Exit")

        opt = input("Choose an option: ")

        if opt == "1":
            add_coop()
        elif opt == "2":
            add_chicken()
        elif opt == "3":
            view_chickens()
        elif opt == "4":
            edit_chicken()
        elif opt == "5":
            delete_chicken()
        elif opt == "6":
            find_chicken()
        elif opt == "0":
            print("Goodbye!")
            break
        else:
            print("Invalid option.\n")


load_data()
main_menu()
