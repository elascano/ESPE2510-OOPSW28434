from chicken_coop import ChickenCoop
from chicken import Chicken
from json_file_manager import JSONFileManager  # Importación corregida

class ChickenFarmSimulator:
    def __init__(self):
        self.coops = []
    
    def main(self):
        print("Welcome to the Chicken Farm Simulator")
        
        self.coops = JSONFileManager.load_from_file()
        
        self.show_main_menu()
    
    def show_main_menu(self):
        option = -1
        while option != 0:
            print("\n=== CHICKEN FARM MAIN MENU ===")
            print("1. Create Chicken Coop")
            print("2. Add Chicken to Coop")
            print("3. List All Coops")
            print("4. List Chickens in Coop")
            print("5. Remove Chicken from Coop")
            print("6. Make Chickens Do Stuff")
            print("7. Remove Coop")
            print("=== JSON FILE OPERATIONS ===")
            print("8. Save Data to JSON File")
            print("9. Load Data from JSON File")
            print("10. Display JSON File Content")
            print("11. Update Chicken Data")
            print("12. Delete Chicken from JSON")
            print("0. Exit")
            
            try:
                option = int(input("Select option: "))
                
                if option == 1:
                    self.create_chicken_coop()
                elif option == 2:
                    self.add_chicken_to_coop()
                elif option == 3:
                    self.list_all_coops()
                elif option == 4:
                    self.list_chickens_in_coop()
                elif option == 5:
                    self.remove_chicken_from_coop()
                elif option == 6:
                    self.make_chickens_do_stuff()
                elif option == 7:
                    self.remove_coop()
                elif option == 8:
                    JSONFileManager.save_to_file(self.coops)
                elif option == 9:
                    self.coops = JSONFileManager.load_from_file()
                elif option == 10:
                    JSONFileManager.display_file_data()
                elif option == 11:
                    self.update_chicken_data()
                elif option == 12:
                    self.delete_chicken_from_json()
                elif option == 0:
                    JSONFileManager.save_to_file(self.coops)
                    print("Goodbye! Thanks for using Chicken Farm Simulator!")
                else:
                    print("Invalid option! Please select a number between 0 and 12.")
                    option = -1
                    
            except ValueError:
                print("Error: Only numbers are allowed. Please enter a valid number.")
                option = -1
            except Exception as e:
                print(f"Unexpected error: {str(e)}")
                option = -1
    
    def create_chicken_coop(self):
        print("\n--- CREATE CHICKEN COOP ---")
        
        while True:
            try:
                coop_id = int(input("Enter Coop ID: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Coop ID.")

        for coop in self.coops:
            if coop.get_id() == coop_id:
                print(f"Coop with ID {coop_id} already exists!")
                return

        description = input("Enter Coop description: ")

        new_coop = ChickenCoop(coop_id, description)
        self.coops.append(new_coop)
        print("Coop created successfully!")
    
    def add_chicken_to_coop(self):
        if not self.coops:
            print("No coops available. Please create a coop first.")
            return
        
        print("\n--- ADD CHICKEN TO COOP ---")
        
        print("Available coops:")
        for coop in self.coops:
            print(f"Coop ID: {coop.get_id()} - {coop.get_description()}")
        
        while True:
            try:
                coop_id = int(input("Enter Coop ID: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Coop ID.")
        
        selected_coop = self.find_coop_by_id(coop_id)
        if not selected_coop:
            print("Coop not found!")
            return
        
        print("\n--- Enter chicken data ---")
        
        while True:
            try:
                chicken_id = int(input("Chicken ID: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Chicken ID.")
        
        name = input("Name: ")
        color = input("Color: ")
        
        while True:
            try:
                age = int(input("Age: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Age.")
        
        is_molting = False
        while True:
            try:
                molting_input = input("Is molting? (1 for true / 0 for false): ")
                molting_value = int(molting_input)
                
                if molting_value == 1:
                    is_molting = True
                    break
                elif molting_value == 0:
                    is_molting = False
                    break
                else:
                    print("Error: Only 1 (true) or 0 (false) are allowed for molting status.")
            except ValueError:
                print("Error: Only numbers are allowed. Please enter 1 for true or 0 for false.")
        
        chicken = Chicken(chicken_id, name, color, age, is_molting)
        selected_coop.add_chicken(chicken)
        print("----Chicken added successfully------")
    
    def list_all_coops(self):
        print("\n--- ALL COOPS ---")
        if not self.coops:
            print("No coops available.")
            return
        
        for coop in self.coops:
            print(coop)
    
    def list_chickens_in_coop(self):
        if not self.coops:
            print("No coops available.")
            return
        
        while True:
            try:
                coop_id = int(input("Enter Coop ID to list chickens: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Coop ID.")
        
        selected_coop = self.find_coop_by_id(coop_id)
        if selected_coop:
            selected_coop.list_chickens()
        else:
            print("Coop not found!")
    
    def remove_chicken_from_coop(self):
        if not self.coops:
            print("No coops available.")
            return
        
        while True:
            try:
                coop_id = int(input("Enter Coop ID: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Coop ID.")
        
        selected_coop = self.find_coop_by_id(coop_id)
        if not selected_coop:
            print("Coop not found!")
            return
        
        while True:
            try:
                chicken_id = int(input("Enter Chicken ID to remove: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Chicken ID.")
        
        selected_coop.remove_chicken(chicken_id)
    
    def make_chickens_do_stuff(self):
        if not self.coops:
            print("No coops available.")
            return
        
        print("\n--- MAKE CHICKENS DO STUFF ---")
        print("1. Make specific chicken do stuff")
        print("2. Make all chickens in coop do stuff")
        
        while True:
            try:
                option = int(input("Select option: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed.")
        
        if option == 1:
            self.make_specific_chicken_do_stuff()
        elif option == 2:
            self.make_all_chickens_in_coop_do_stuff()
        else:
            print("Invalid option! Please select 1 or 2.")
    
    def make_specific_chicken_do_stuff(self):
        while True:
            try:
                coop_id = int(input("Enter Coop ID: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Coop ID.")
        
        while True:
            try:
                chicken_id = int(input("Enter Chicken ID: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Chicken ID.")
        
        selected_coop = self.find_coop_by_id(coop_id)
        if selected_coop:
            chicken = selected_coop.find_chicken_by_id(chicken_id)
            if chicken:
                print(f"\n--- {chicken.get_name()} IS DOING STUFF ---")
                chicken.do_stuff()
            else:
                print("Chicken not found!")
        else:
            print("Coop not found!")
    
    def make_all_chickens_in_coop_do_stuff(self):
        while True:
            try:
                coop_id = int(input("Enter Coop ID: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Coop ID.")
        
        selected_coop = self.find_coop_by_id(coop_id)
        if selected_coop:
            selected_coop.make_all_do_stuff()
        else:
            print("Coop not found!")
    
    def remove_coop(self):
        if not self.coops:
            print("No coops available.")
            return
        
        while True:
            try:
                coop_id = int(input("Enter Coop ID to remove: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Coop ID.")
        
        coop_to_remove = self.find_coop_by_id(coop_id)
        if coop_to_remove:
            self.coops.remove(coop_to_remove)
            print("Coop removed successfully!")
        else:
            print("Coop not found!")
    
    def update_chicken_data(self):
        if not self.coops:
            print("No coops available. Please create a coop and add chickens first.")
            return
        
        has_chickens = any(coop.get_chicken_count() > 0 for coop in self.coops)
        if not has_chickens:
            print("No chickens available. Please add chickens first.")
            return
        
        print("\n--- UPDATE CHICKEN DATA ---")
        
        print("Available chickens:")
        for coop in self.coops:
            if coop.get_chicken_count() > 0:
                print(f"Coop {coop.get_id()} - {coop.get_description()}:")
                coop.list_chickens()
        
        while True:
            try:
                chicken_id = int(input("Enter Chicken ID to update: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Chicken ID.")
        
        new_name = input("Enter new name: ")
        new_color = input("Enter new color: ")
        
        while True:
            try:
                new_age = int(input("Enter new age: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Age.")
        
        new_molting_status = False
        while True:
            try:
                molting_input = input("Is molting? (1 for true / 0 for false): ")
                molting_value = int(molting_input)
                
                if molting_value == 1:
                    new_molting_status = True
                    break
                elif molting_value == 0:
                    new_molting_status = False
                    break
                else:
                    print("Error: Only 1 (true) or 0 (false) are allowed for molting status.")
            except ValueError:
                print("Error: Only numbers are allowed. Please enter 1 for true or 0 for false.")
        
        JSONFileManager.update_chicken_data(self.coops, chicken_id, new_name, new_color, new_age, new_molting_status)
    
    def delete_chicken_from_json(self):
        if not self.coops:
            print("No coops available. Please create a coop and add chickens first.")
            return
        
        has_chickens = any(coop.get_chicken_count() > 0 for coop in self.coops)
        if not has_chickens:
            print("No chickens available. Please add chickens first.")
            return
        
        print("\n--- DELETE CHICKEN FROM JSON ---")
        
        print("Available chickens:")
        for coop in self.coops:
            if coop.get_chicken_count() > 0:
                print(f"Coop {coop.get_id()} - {coop.get_description()}:")
                coop.list_chickens()
        
        while True:
            try:
                coop_id = int(input("Enter Coop ID: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Coop ID.")
        
        while True:
            try:
                chicken_id = int(input("Enter Chicken ID to delete: "))
                break
            except ValueError:
                print("Error: Only numbers are allowed for Chicken ID.")
        
        JSONFileManager.delete_chicken_from_file(self.coops, coop_id, chicken_id)
    
    def find_coop_by_id(self, coop_id):
        for coop in self.coops:
            if coop.get_id() == coop_id:
                return coop
        return None

# Ejecutar la aplicación
if __name__ == "__main__":
    simulator = ChickenFarmSimulator()
    simulator.main()