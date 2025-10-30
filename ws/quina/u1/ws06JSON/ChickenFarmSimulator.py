import json
from datetime import date
from ChickenCoop import ChickenCoop
from Chicken import Chicken

class ChickenFarmSimulator:
    def __init__(self):
        self.coop = ChickenCoop(1, "Coop A")
        self.load_data()

    def save_data(self):
        with open("chickens.json", "w") as file:
            json.dump(self.coop.to_dict(), file, indent=4)
        print("Data saved to chickens.json")

    def load_data(self):
        try:
            with open("chickens.json", "r") as file:
                data = json.load(file)
                self.coop = ChickenCoop(data["id"], data["name"])
                for c in data["chickens"]:
                    chicken = Chicken(c["id"], c["name"], c["color"], c["is_molting"], c["age"])
                    self.coop.add_chicken(chicken)
                print("Data loaded successfully.")
        except FileNotFoundError:
            print("No existing data found. Starting with an empty coop.")

    def add_chicken(self):
        try:
            chicken_id = int(input("Enter chicken ID: "))
            name = input("Enter chicken name: ")
            color = input("Enter chicken color: ")
            age = int(input("Enter chicken age: "))
            is_molting = input("Is the chicken molting? (yes/no): ").lower() == "yes"
            chicken = Chicken(chicken_id, name, color, is_molting, age)
            self.coop.add_chicken(chicken)
            self.save_data()
        except ValueError:
            print("Invalid input. Try again.")

    def show_chickens(self):
        self.coop.show_coop()

    def chicken_action(self):
        if not self.coop.chickens:
            print("No chickens available.")
            return

        name = input("Enter the name of the chicken: ")
        chicken = next((c for c in self.coop.chickens if c.name.lower() == name.lower()), None)
        if not chicken:
            print("Chicken not found.")
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
            print("\n=== Chicken Farm Simulator ===")
            print("1. Add Chicken")
            print("2. Show Chickens")
            print("3. Chicken Actions")
            print("4. Exit")
            choice = input("Choose an option: ")

            if choice == "1":
                self.add_chicken()
            elif choice == "2":
                self.show_chickens()
            elif choice == "3":
                self.chicken_action()
            elif choice == "4":
                self.save_data()
                print("Goodbye!")
                break
            else:
                print("Invalid option. Try again.")

if __name__ == "__main__":
    simulator = ChickenFarmSimulator()
    simulator.run()