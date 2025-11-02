import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
import json
from ChickenFarmModel.ChickenCoop import ChickenCoop
from ChickenFarmModel.Chicken import Chicken

FILE_NAME = "ChickenCoops_data.json" 

def save_data(coops):
    try:
        data_to_save = [c.to_dict() for c in coops]
        with open(FILE_NAME, "w") as file:
            json.dump(data_to_save, file, indent=4)
    except Exception as e:
        print(f"Error saving data: {e}")

def load_data():
    coops = []
    
    try:
        with open(FILE_NAME, "r") as file:
            data_list = json.load(file)
            for data in data_list:
                coop = ChickenCoop(data["id"], data["name"])
                for c in data["chickens"]:
                    chicken = Chicken(c["id"], c["name"], c["color"], c["is_molting"], c["age"])
                    coop.add_chicken(chicken, silent=True)
                coops.append(coop)
            
    except FileNotFoundError:
        coop_A = ChickenCoop(1, "Coop A")
        coop_B = ChickenCoop(2, "Coop B")
        
        chickens_to_add = [
            Chicken(chicken_id=1, name="Lucy", color="Brown", is_molting=False, age=1),
            Chicken(chicken_id=2, name="Clara", color="Black", is_molting=True, age=3),
            Chicken(chicken_id=3, name="Coco", color="White", is_molting=False, age=2),
            Chicken(chicken_id=4, name="Lili", color="Black", is_molting=False, age=1),
            Chicken(chicken_id=5, name="Juana", color="White", is_molting=True, age=4)
        ]

        for chicken in chickens_to_add:
            coop_A.add_chicken(chicken, silent=True)
            
        coops.append(coop_A)
        coops.append(coop_B)
        
        save_data(coops) 
        
    except Exception as e:
        print(f"Error loading data: {e}")
        
    return coops 

class ChickenFarmSimulator:
    def __init__(self):
        self.coops = load_data() 

    def _get_coop_by_name(self, name):
        return next((c for c in self.coops if c.name.lower() == name.lower()), None)

    def add_chicken(self):
        coop_name = input("Enter the name of the coop to add the chicken to (e.g., Coop A): ")
        target_coop = self._get_coop_by_name(coop_name)

        if not target_coop:
            print(f"Error: Coop '{coop_name}' not found.")
            return

        try:
            chicken_id = int(input("Enter chicken ID: "))
            
            if any(c.id == chicken_id for c in target_coop.chickens):
                print(f"Error: Chicken ID {chicken_id} already exists in {coop_name}. Please use a unique ID.")
                return
            
            name = input("Enter chicken name: ")
            color = input("Enter chicken color: ")
            age = int(input("Enter chicken age: "))
            is_molting = input("Is the chicken molting? (yes/no): ").lower() == "yes"
            chicken = Chicken(chicken_id, name, color, is_molting, age)
            
            target_coop.add_chicken(chicken)
            save_data(self.coops)
            
        except ValueError:
            print("Invalid input (ID or Age must be a number). Try again.")

    def show_chickens(self):
        if not self.coops:
            print("No coops available in the farm.")
            return

        print("\n--- FARM OVERVIEW ---")
        for coop in self.coops:
            coop.show_coop()
        print("---------------------------------")

    def update_chicken(self):
        coop_name = input("Enter the name of the coop where the chicken is located (e.g., Coop A): \n")
        target_coop = self._get_coop_by_name(coop_name)

        if not target_coop:
            print(f"Error: Coop '{coop_name}' not found.")
            return
            
        name = input("Enter the name of the chicken to update: ")
        chicken = target_coop.get_chicken_by_name(name)
        
        if not chicken:
            print("Chicken not found in the specified coop.")
            return
        
        print(f"--- Updating Chicken: {chicken.name} in {coop_name} ---")
        
        new_color = input(f"New color (current: {chicken.color}, leave empty to skip): ") or None
        new_age_str = input(f"New age (current: {chicken.age}, leave empty to skip): ")
        new_molting_str = input(f"Is molting? (current: {'yes' if chicken.is_molting else 'no'}, enter yes/no/empty): ").lower()
        
        new_age = int(new_age_str) if new_age_str.isdigit() else None
        
        new_molting = None
        if new_molting_str == "yes":
            new_molting = True
        elif new_molting_str == "no":
            new_molting = False
            
        if target_coop.update_chicken(name, new_color, new_age, new_molting):
            save_data(self.coops)
        else:
            print("No changes were made or an error occurred.")


    def remove_chicken(self):
        coop_name = input("Enter the name of the coop to remove the chicken from (e.g., Coop A):\n ")
        target_coop = self._get_coop_by_name(coop_name)

        if not target_coop:
            print(f"Error: Coop '{coop_name}' not found.")
            return

        if not target_coop.chickens:
            print(f"Coop '{coop_name}' has no chickens to remove.")
            return

        name = input("Enter the name of the chicken to remove: ")
        
        if target_coop.remove_chicken(name):
            save_data(self.coops)
        else:
            print("Chicken not found in the specified coop.")

    def chicken_action(self):
        coop_name = input("Enter the name of the coop where the chicken is located: ")
        target_coop = self._get_coop_by_name(coop_name)

        if not target_coop:
            print(f"Error: Coop '{coop_name}' not found.")
            return

        if not target_coop.chickens:
            print(f"Coop '{coop_name}' has no chickens for actions.")
            return

        name = input("Enter the name of the chicken: ")
        chicken = target_coop.get_chicken_by_name(name)

        if not chicken:
            print("Chicken not found in the specified coop.")
            return

        print("\n1. Cluck")
        print("2. Eat")
        print("3. Lay egg")
        print("4. Poop")
        choice = input("Choose an action: ")

        if choice == "1":
            chicken.cluck()
        elif choice == "2":
            chicken.eat()
        elif choice == "3":
            chicken.lay_egg()
        elif choice == "4":
            chicken.poop()
        else:
            print("Invalid option.")

    def run(self):
        while True:
            print("\n=== CHICKEN FARM SIMULATOR ===\n")
            print("1. Add Chicken")
            print("2. Show Chickens")
            print("3. Chicken Actions")
            print("4. Update Chicken") 
            print("5. Remove Chicken") 
            print("6. Exit")
            choice = input("Choose an option: ")

            if choice == "1":
                self.add_chicken()
            elif choice == "2":
                self.show_chickens()
            elif choice == "3":
                self.chicken_action()
            elif choice == "4":
                self.update_chicken()
            elif choice == "5":
                self.remove_chicken()
            elif choice == "6":
                save_data(self.coops)
                print("Goodbye!")
                break
            else:
                print("Invalid option. Try again.")

if __name__ == "__main__":
    simulator = ChickenFarmSimulator()
    simulator.run()