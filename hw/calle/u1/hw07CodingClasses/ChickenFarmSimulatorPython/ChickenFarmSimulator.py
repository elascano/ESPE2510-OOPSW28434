from chicken import Chicken
from poop import make_chicken_poop
from egg import lay_egg

class ChickenFarmSimulator:
    def __init__(self):
        self.chickens = []
        self.initialize_chickens()
    
    def initialize_chickens(self):
        self.chickens.append(Chicken(1, "Lucy", "White and Brown", False, 2))
        self.chickens.append(Chicken(2, "Maruja", "white", True, 1))
    
    def add_new_chicken(self):
        print("\n--- Add New Chicken ---")
        
        next_id = 1 if not self.chickens else self.chickens[-1].id + 1
        
        name = input("Chicken name: ").strip()
        color = input("Color: ").strip()
        
        while True:
            age_input = input("Age (years): ").strip()
            try:
                age = int(age_input)
                if age < 0:
                    print("Error: Age cannot be negative")
                    continue
                break
            except ValueError:
                print("Error: Please enter a valid number for age")
        
        while True:
            molting_input = input("isMolting (true/false): ").strip().lower()
            if molting_input in ['true', 'false']:
                is_molting = molting_input == 'true'
                break
            else:
                print("Please enter 'true' or 'false'")
        
        new_chicken = Chicken(next_id, name, color, is_molting, age)
        self.chickens.append(new_chicken)
        
        print("Chicken added successfully!")
        print(f"Assigned ID: {next_id}")
    
    def show_all_chickens(self):
        print("\n--- All Registered Chickens ---")
        if not self.chickens:
            print("No chickens registered.")
            return
        
        for chicken in self.chickens:
            print("the chicken is ---> Chicken{")
            print(f"id -> {chicken.id}")
            print(f"name -> {chicken.name}")
            print(f"color -> {chicken.color}")
            print(f"age -> {chicken.age}")
            print(f"isMolting -> {chicken.is_molting}")
            print("}")
        
        self.make_all_chickens_act()
    
    def wander(self, chicken):
        print(f"chicken {chicken.name} is wandering")
    
    def drink(self, chicken):
        print(f"chicken {chicken.name} is drinking")
    
    def make_all_chickens_act(self):
        print("\n--- Chicken Actions ---")
        for chicken in self.chickens:
            print(f"chicken id --> {chicken.id} {chicken.name}")
            chicken.cluck()
            chicken.eat()
            chicken.cluck()
            make_chicken_poop(chicken, 2)
            make_chicken_poop(chicken, 3)
            chicken.eat()
            self.wander(chicken)
            self.drink(chicken)
            lay_egg(chicken, "M")
            lay_egg(chicken, "L")
    
    def run(self):
        print("This is my Chicken Farm Simulator")
        
        while True:
            print("\n--- Main Menu ---")
            print("1. Add new chicken")
            print("2. Show all chickens")
            print("3. Exit")
            
            try:
                option = input("Select an option: ").strip()
                if not option:
                    continue
                option = int(option)
                
                if option == 1:
                    self.add_new_chicken()
                elif option == 2:
                    self.show_all_chickens()
                elif option == 3:
                    break
                else:
                    print("Invalid option")
            except ValueError:
                print("Please enter a valid number (1, 2, or 3)")
        
        print("\nBUILD SUCCESSFUL")

if __name__ == "__main__":
    try:
        farm = ChickenFarmSimulator()
        farm.run()
        input("\nPress Enter to exit...")
    except KeyboardInterrupt:
        print("\n\nProgram interrupted by user")
    except Exception as e:
        print(f"\nAn error occurred: {e}")