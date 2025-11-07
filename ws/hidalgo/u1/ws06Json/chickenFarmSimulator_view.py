from chicken_model import Chicken
from chickenCoop_model import ChickenCoop
from egg_model import Egg
from poop_model import Poop
from farmer_model import Farmer

class ChickenFarmSimulator:
    def __init__(self):
        self.coop = ChickenCoop()
       
        self.menu_actions = {
            "1": self.addChicken,
            "2": self.showChicken,
            "3": self.exit_program
        }
        self.welcome_message = "\n=== Welcome to my Chicken Farm Simulator ==="

    def showMenu(self):
        print(self.welcome_message)
        print("1. Register New Chickens")
        print("2. Display the Flock")
        print("3. Quit")

    def askChickensNumber(self):
        
        while True:
            try:
                count_str = input("Enter the quantity of chickens to register: ")
                count = int(count_str)
                if count < 0:
                    raise ValueError
                return count
            except ValueError:
                print("Invalid input. Please enter a positive integer.")

    def askChickenInformation(self):
        print("\n--- New Chicken Entry ---")
        
        _id = input("ID (must be unique): ")
        _name = input("Name/Alias: ")
        _color = input("Feather Color: ")
        while True:
            try:
                _age = int(input("Age (in years): "))
                break
            except ValueError:
                print("Age must be a whole number.")
        
        
        _molting_input = input("Is the chicken undergoing a molt? (y/n): ").strip().lower()
        _is_molting = (_molting_input == "y" or _molting_input == "yes")
        
       
        return Chicken(_id, _name, _color, _age, _is_molting)

    def addChicken(self):
        amount_to_add = self.askChickensNumber()
        for i in range(amount_to_add):
            print(f"--- Chicken {i+1} of {amount_to_add} ---")
            chicken_instance = self.askChickenInformation()
            self.coop.addChicken(chicken_instance)
        
       
        self.coop.saveToJson(filename="farm_chickens.json") 
        print("All chickens registered and data saved!")

    def showChicken(self):
        
        self.coop.loadFromJson(filename="farm_chickens.json") 
        flock = self.coop.getChickens()
        
        if not flock:
            print("The coop is empty. No chickens to display.")
        else:
            print(f"\n--- Current Flock ({len(flock)} members) ---")
            
            for c in flock:
                molting_status = "YES" if c.is_molting else "NO"
                print(f"** {c.name} ** (ID: {c.id})")
                print(f"  > Color: {c.color} | Age: {c.age} yrs | Molting: {molting_status}")
                print(f"  > Status: {c.cluck()}")
            print("--- End of List ---")

    def exit_program(self):
        print("Exiting simulator. Farewell!")
        
        raise SystemExit

    def run(self):
        while True:
            self.showMenu()
            opcion = input("Select an option: ").strip()

           
            action = self.menu_actions.get(opcion)
            if action:
                try:
                    action()
                except SystemExit:
                    break
                except Exception as e:
                    print(f"An unexpected error occurred: {e}")
            else:
                print("Option not recognized. Please choose 1, 2, or 3.")

if __name__ == "__main__":
    simulation_app = ChickenFarmSimulator()
    simulation_app.run()