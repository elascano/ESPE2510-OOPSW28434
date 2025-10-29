from chicken_model import Chicken
from chickenCoop_model import ChickenCoop
from egg_model import Egg
from poop_model import Poop
from farmer_model import Farmer

class ChickenFarmSimulator:
    def __init__(self):
        self.coop = ChickenCoop()

    def showMenu(self):
        print("\n Welcome to my Chicken Farm Simulator")
        print("1. Add Chickens")
        print("2. Visualize the chickens")
        print("3. Exit")

    def askChickensNumber(self):
        while True:
            try:
                return int(input("How many chickens do you want to save? "))
            except ValueError:
                print("Please enter a valid number")

    def askChickenInformation(self):
        print("\nChicken")
        id = input("ID: ")
        name = input("Name: ")
        color = input("Color: ")
        while True:
            try:
                age = int(input("Age: "))
                break
            except ValueError:
                print("Please enter a valid number")
        is_molting = input("Is molting? (y/n): ").lower() == "y"
        return Chicken(id, name, color, age, is_molting)

    def addChicken(self):
        amount = self.askChickensNumber()
        for _ in range(amount):
            chicken = self.askChickenInformation()
            self.coop.addChicken(chicken)
        self.coop.saveToJson()
        print("Chickens save!")

    def showChicken(self):
        self.coop.loadFromJson()
        chickens = self.coop.getChickens()
        if not chickens:
            print("There aren't chickens.")
        else:
            print(f"It founds {len(chickens)} chickens:\n")
            for chicken in chickens:
                print(f"ID: {chicken.id} | Name: {chicken.name} | Color: {chicken.color} | Age: {chicken.age} | Molting: {chicken.is_molting}")
                print(chicken.cluck())

    def run(self):
        while True:
            self.showMenu()
            opcion = input("Choose an option ")

            if opcion == "1":
                self.addChicken()
            elif opcion == "2":
                self.showChicken()
            elif opcion == "3":
                print("Byeee ")
                break
            else:
                print("Invalid option, please try again")

if __name__ == "__main__":
    app = ChickenFarmSimulator()
    app.run()