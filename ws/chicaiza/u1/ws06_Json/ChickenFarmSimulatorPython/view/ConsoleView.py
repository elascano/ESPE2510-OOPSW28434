from controller.FarmController import FarmController
from controller.ChickenController import ChickenController
from model.ChickenFarmer import ChickenFarmer
from model.ChickenCoop import ChickenCoop
from model.Chicken import Chicken
import random

class ConsoleView:
    def __init__(self):
        self.farm_controller = FarmController()
        self.chicken_controller = ChickenController()

    def start(self):
        while True:
            print("\n===== CHICKEN FARM MENU =====")
            print("1. Create a farm")
            print("2. Add a chicken to a farm")
            print("3. Make a chicken perform an action")
            print("4. View all data")
            print("5. Exit")

            option = input("Select option: ")

            if option == "1":
                self.create_farm()
            elif option == "2":
                self.add_chicken()
            elif option == "3":
                self.chicken_action()
            elif option == "4":
                self.view_all_data()
            elif option == "5":
                print("Goodbye!")
                break
            else:
                print("Invalid option.")

    def create_farm(self):
        name = input("Farmer name: ")
        coop_id = int(input("Coop ID: "))
        farmer = ChickenFarmer(name)
        farmer.add_coop(ChickenCoop(coop_id))
        self.farm_controller.add_farmer(farmer)
        print("Farm created successfully.")

    def add_chicken(self):
        farmer_name = input("Farmer name: ")
        coop_id = int(input("Coop ID: "))
        name = input("Chicken name: ")
        color = input("Chicken color: ")
        age = int(input("Chicken age: "))

        chicken = Chicken(random.randint(1, 9999), name, color, age, False)
        self.farm_controller.add_chicken_to_coop(farmer_name, coop_id, chicken)
        print("Chicken added successfully.")

    def chicken_action(self):
        name = input("Chicken name: ")
        action = input("Action (cluck/eat/poop/egg): ")
        chicken = Chicken(0, name, "brown", 2, False)
        self.chicken_controller.make_chicken_do_something(chicken, action)

    def view_all_data(self):
        farmers = self.farm_controller.get_farmers()
        if not farmers:
            print("No data available.")
            return
        
        print("\n=== CURRENT DATA ===")
        for farmer in farmers:
            print(f"Farmer: {farmer.name}")
            for coop in farmer.coops:
                print(f"  Coop ID: {coop.id}")
                if coop.chickens:
                    for chicken in coop.chickens:
                        print(f"    Chicken ID: {chicken.id}, Name: {chicken.name}, Color: {chicken.color}, Age: {chicken.age}")
                else:
                    print("    No chickens in this coop.")