import json
import os
from Chicken import Chicken
from ChickenCoop import ChickenCoop


class ChickenFarmMenu:
    def __init__(self):
        self.coops = []
        self.file_name = os.path.join(os.path.dirname(__file__), "Chickens.json")
        self.load_chickens()

    def ensure_json_exists(self):
        if not os.path.exists(self.file_name):
            default_coops = [ChickenCoop("Happy Hens Coop"), ChickenCoop("Sunrise Nest")]
            self.coops = default_coops
            self.save_chickens()

    def load_chickens(self):
        self.ensure_json_exists()
        try:
            with open(self.file_name, "r", encoding="utf-8") as file:
                chickens_data = json.load(file)
            if not chickens_data:
                raise ValueError("Empty file")
            coops_dict = {}
            for ch in chickens_data:
                coop_name = ch["CoopName"]
                if coop_name not in coops_dict:
                    coops_dict[coop_name] = ChickenCoop(coop_name)
                chicken = Chicken(
                    ch["Name"],
                    ch["Color"],
                    ch["Age"],
                    ch["Molting"],
                    ch["ID"]
                )
                coops_dict[coop_name].add_chicken(chicken)
            self.coops = list(coops_dict.values())
        except Exception:
            self.coops = [ChickenCoop("Happy Hens Coop"), ChickenCoop("Sunrise Nest")]
            self.save_chickens()

    def save_chickens(self):
        all_chickens = []
        for coop in self.coops:
            for ch in coop.get_chickens():
                all_chickens.append({
                    "ID": ch.id,
                    "Name": ch.name,
                    "Color": ch.color,
                    "Age": ch.age,
                    "Molting": ch.is_molting,
                    "CoopName": coop.name
                })
        with open(self.file_name, "w", encoding="utf-8") as file:
            json.dump(all_chickens, file, indent=2)

    def display_chickens(self, coop):
        print(f"\n          ----Chickens in {coop.name}----  ")
        chickens = coop.get_chickens()
        if not chickens:
            print("No chickens found.")
            return
        headers = ["ID", "Name", "Color", "Age", "Molting"]
        col_widths = [5, 15, 20, 5, 10]
        def format_row(cols):
            return "".join(str(col).ljust(col_widths[i]) for i, col in enumerate(cols))
        print(format_row(headers))
        print(format_row(["-" * len(h) for h in headers]))
        for ch in chickens:
            row = [ch.id, ch.name, ch.color, ch.age, "Yes" if ch.is_molting else "No"]
            print(format_row(row))

    def get_user_input(self, prompt):
        return input(prompt).strip()

    def select_coop(self):
        while True:
            print("\nSelect a Coop:")
            for i, c in enumerate(self.coops, 1):
                print(f"{i}. {c.name}")
            choice = self.get_user_input("Enter your choice: ")
            if choice.isdigit() and 1 <= int(choice) <= len(self.coops):
                return self.coops[int(choice) - 1]
            print("Invalid input. Please enter a valid number.")

    def visualize_chickens(self):
        coop = self.select_coop()
        self.display_chickens(coop)

    def add_chicken(self):
        coop = self.select_coop()
        name = self.get_user_input("Enter chicken name: ")
        color = self.get_user_input("Enter chicken color: ")
        while True:
            try:
                age = int(self.get_user_input("Enter chicken age (number): "))
                if age > 0:
                    break
            except ValueError:
                pass
            print("Invalid age. Enter a valid number.")
        while True:
            molting_input = self.get_user_input("Is the chicken molting? (yes/no): ").lower()
            if molting_input in ["yes", "no", "y", "n"]:
                break
            print("Invalid input. Enter yes or no.")
        is_molting = molting_input.startswith("y")
        next_id = len(coop.get_chickens()) + 1
        new_chicken = Chicken(name, color, age, is_molting, next_id)
        coop.add_chicken(new_chicken)
        self.save_chickens()
        print("\nThe chicken is clucking! Added successfully.\n")
        self.after_action_prompt()

    def find_chicken_by_id(self, coop, id):
        for c in coop.get_chickens():
            if c.id == id:
                return c
        return None

    def prompt_for_id(self, coop):
        while True:
            try:
                id_val = int(self.get_user_input("Enter chicken ID: "))
                chicken = self.find_chicken_by_id(coop, id_val)
                if chicken:
                    return chicken
                print("No chicken found with that ID in this coop.")
            except ValueError:
                print("Invalid input. Enter a valid number.")

    def search_chicken(self):
        coop = self.select_coop()
        chicken = self.prompt_for_id(coop)
        self.display_single_chicken(coop, chicken)
        self.after_action_prompt()

    def display_single_chicken(self, coop, ch):
        print(f"\n          ----Chicken in {coop.name}----  ")
        headers = ["ID", "Name", "Color", "Age", "Molting"]
        col_widths = [5, 15, 20, 5, 10]
        def format_row(cols):
            return "".join(str(col).ljust(col_widths[i]) for i, col in enumerate(cols))
        print(format_row(headers))
        print(format_row(["-" * len(h) for h in headers]))
        row = [ch.id, ch.name, ch.color, ch.age, "Yes" if ch.is_molting else "No"]
        print(format_row(row))

    def edit_chicken(self):
        coop = self.select_coop()
        chicken = self.prompt_for_id(coop)
        while True:
            self.display_single_chicken(coop, chicken)
            new_name = self.get_user_input(f'Enter new name (press enter to keep "{chicken.name}"): ')
            if new_name:
                chicken.name = new_name
            new_color = self.get_user_input(f'Enter new color (press enter to keep "{chicken.color}"): ')
            if new_color:
                chicken.color = new_color
            new_age_input = self.get_user_input(f'Enter new age (press enter to keep "{chicken.age}"): ')
            if new_age_input:
                try:
                    new_age = int(new_age_input)
                    if new_age > 0:
                        chicken.age = new_age
                except ValueError:
                    print("Invalid age. Enter a valid number.")
            molting_input = self.get_user_input(
                f'Is the chicken molting? (yes/no, press enter to keep "{ "Yes" if chicken.is_molting else "No" }"): '
            ).lower()
            if molting_input in ["yes", "no", "y", "n"]:
                chicken.is_molting = molting_input.startswith("y")
            confirm = self.get_user_input("Are you sure the modification is correct? (1. Yes / 2. Edit again): ")
            if confirm == "1":
                break
        self.save_chickens()
        print("\nChicken updated successfully.\n")
        self.after_action_prompt()

    def delete_chicken(self):
        coop = self.select_coop()
        chicken = self.prompt_for_id(coop)
        self.display_single_chicken(coop, chicken)
        while True:
            confirm = self.get_user_input("Are you sure you want to delete this chicken? (1. Yes / 2. No): ")
            if confirm == "1":
                chickens = coop.get_chickens()
                chickens.remove(chicken)
                for i, c in enumerate(chickens, 1):
                    c.id = i
                self.save_chickens()
                print("Chicken deleted successfully.")
                break
            elif confirm == "2":
                print("Deletion cancelled.")
                break
            else:
                print("Invalid input. Enter 1 or 2.")
        self.after_action_prompt()

    def after_action_prompt(self):
        while True:
            print("\n1. Return to Menu")
            print("2. Exit Program")
            choice = self.get_user_input("Enter your choice: ")
            if choice == "1":
                return
            if choice == "2":
                print("Exiting the program.")
                exit(0)
            print("Invalid input. Enter 1 or 2.")

    def main_menu(self):
        print("\n  ---Welcome to the Chicken Farm Simulator---  ")
        while True:
            print("\nMain Menu:")
            print("1. View Chickens")
            print("2. Add Chicken")
            print("3. Edit Chicken")
            print("4. Delete Chicken")
            print("5. Search Chicken")
            print("6. Exit")
            choice = self.get_user_input("Enter your choice: ")
            if not choice.isdigit():
                print("Invalid input. Please enter a valid option.")
                continue
            num = int(choice)
            if num == 1:
                self.visualize_chickens()
            elif num == 2:
                self.add_chicken()
            elif num == 3:
                self.edit_chicken()
            elif num == 4:
                self.delete_chicken()
            elif num == 5:
                self.search_chicken()
            elif num == 6:
                print("Exiting the program.")
                break
            else:
                print("Invalid input. Please enter a valid option.")


if __name__ == "__main__":
    menu = ChickenFarmMenu()
    menu.main_menu()