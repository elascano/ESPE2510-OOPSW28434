from coop import Coop
from chicken import Chicken

class Farmer:
    def __init__(self):
        self.coop = Coop()

    def add_chicken(self):
        print("\n--- Add New Chicken ---")
        name = input("Name: ")
        color = input("Color: ")
        age = int(input("Age (in years): "))

        molting_input = input("Is molting? (True/False): ").strip().lower()
        if molting_input not in ["true", "false"]:
            print("Invalid input. Defaulting to False.")
            is_molting = False
        else:
            is_molting = molting_input == "true"

       
        id = self.coop.generate_id()
        chicken = Chicken(id, name, color, age, is_molting)

        chicken.cluck()

        self.coop.save_chicken(chicken)
        print(f"Chicken {name} added successfully!\n")

    def read_chickens(self):
        chickens = self.coop.read_chickens()
        if not chickens:
            print("No chickens registered.")
        else:
            print("\n Chicken List:")
            for c in chickens:
                molting_status = "True" if c["is_molting"] else "False"
                print(f"- ID: {c['id']} | Name: {c['name']} | Color: {c['color']} | Age: {c['age']} years | Molting?: {molting_status}")
