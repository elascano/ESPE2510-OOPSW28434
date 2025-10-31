import json
import os
from model.farmer import Farmer
from model.chickenCoop import ChickenCoop  # ← IMPORTACIÓN ACTUALIZADA
from model.chicken import Chicken

class ChickenFarmMenu:
    def __init__(self):
        self.farmers = []
        self.coops = []
        self.chickens = []
        self.current_farmer = None
        self.data_dir = "farmChickenData"
        
        # Crear directorio de datos si no existe
        if not os.path.exists(self.data_dir):
            os.makedirs(self.data_dir)
        
        self.load_data()
    
    def load_data(self):
        """Cargar datos desde los archivos JSON"""
        try:
            # Cargar granjeros
            if os.path.exists(f"{self.data_dir}/farmers.json"):
                with open(f"{self.data_dir}/farmers.json", 'r') as f:
                    farmers_data = json.load(f)
                    self.farmers = [Farmer.from_dict(data) for data in farmers_data]
            
            # Cargar gallineros
            if os.path.exists(f"{self.data_dir}/coops.json"):
                with open(f"{self.data_dir}/coops.json", 'r') as f:
                    coops_data = json.load(f)
                    self.coops = [ChickenCoop.from_dict(data) for data in coops_data]
            
            print(f"Loaded {len(self.farmers)} farmers and {len(self.coops)} coops from {self.data_dir}")
        
        except Exception as e:
            print(f"Error loading data: {e}")
    
    def save_data(self):
        """Guardar datos en archivos JSON separados"""
        try:
            # Guardar granjeros
            with open(f"{self.data_dir}/farmers.json", 'w') as f:
                json.dump([farmer.to_dict() for farmer in self.farmers], f, indent=2)
            
            # Guardar gallineros
            with open(f"{self.data_dir}/coops.json", 'w') as f:
                json.dump([coop.to_dict() for coop in self.coops], f, indent=2)
            
            print(f"Data saved to {self.data_dir}")
        
        except Exception as e:
            print(f"Error saving data: {e}")
    
    def main_menu(self):
        """Menú principal"""
        while True:
            print("\n=== Kevin Chalan's Chicken Farm ===")
            print("1. Farmer Management")
            print("2. Chicken Coop Management")
            print("3. Chicken Management")
            print("4. Exit")
            
            choice = input("Choose an option: ").strip()
            
            if choice == '1':
                self.farmer_management_menu()
            elif choice == '2':
                self.coop_management_menu()
            elif choice == '3':
                self.chicken_management_menu()
            elif choice == '4':
                self.save_data()
                print("Goodbye!")
                break
            else:
                print("Invalid option. Please try again.")
    
    def farmer_management_menu(self):
        """Menú de gestión de granjeros"""
        while True:
            print("\n--- Farmer Management ---")
            print("1. Create new farmer")
            print("2. Select current farmer")
            print("3. View all farmers")
            print("4. Back to main menu")
            
            choice = input("Choose an option: ").strip()
            
            if choice == '1':
                self.create_farmer()
            elif choice == '2':
                self.select_farmer()
            elif choice == '3':
                self.view_farmers()
            elif choice == '4':
                break
            else:
                print("Invalid option.")
    
    def create_farmer(self):
        """Crear un nuevo granjero"""
        print("\n--- Create New Farmer ---")
        farmer_id = int(input("Farmer ID: "))
        name = input("Farmer Name: ").strip()
        
        # Verificar si el ID ya existe
        if any(farmer.farmer_id == farmer_id for farmer in self.farmers):
            print("Farmer ID already exists!")
            return
        
        new_farmer = Farmer(farmer_id, name)
        self.farmers.append(new_farmer)
        print(f"Farmer '{name}' created successfully!")
        self.save_data()
    
    def select_farmer(self):
        """Seleccionar granjero actual"""
        if not self.farmers:
            print("No farmers available. Please create a farmer first.")
            return
        
        print("\n--- Select Farmer ---")
        for farmer in self.farmers:
            print(farmer)
        
        try:
            farmer_id = int(input("Enter Farmer ID to select: "))
            self.current_farmer = next((f for f in self.farmers if f.farmer_id == farmer_id), None)
            
            if self.current_farmer:
                print(f"Current farmer: {self.current_farmer.name}")
            else:
                print("Farmer not found!")
        except ValueError:
            print("Please enter a valid number.")
    
    def view_farmers(self):
        """Ver todos los granjeros"""
        if not self.farmers:
            print("No farmers available.")
            return
        
        print("\n--- All Farmers ---")
        for farmer in self.farmers:
            print(farmer)
    
    def coop_management_menu(self):
        """Menú de gestión de gallineros"""
        if not self.current_farmer:
            print("Please select a farmer first!")
            return
        
        while True:
            print(f"\n--- Coop Management - Farmer: {self.current_farmer.name} ---")
            print("1. Add chicken coop")
            print("2. View my coops")
            print("3. Back to main menu")
            
            choice = input("Choose an option: ").strip()
            
            if choice == '1':
                self.add_chicken_coop()
            elif choice == '2':
                self.view_my_coops()
            elif choice == '3':
                break
            else:
                print("Invalid option.")
    
    def add_chicken_coop(self):
        """Agregar gallinero"""
        print("\n--- Add Chicken Coop ---")
        coop_id = int(input("Coop ID: "))
        
        # Verificar si el ID ya existe
        if any(coop.coop_id == coop_id for coop in self.coops):
            print("Coop ID already exists!")
            return
        
        new_coop = ChickenCoop(coop_id, self.current_farmer.farmer_id)
        self.coops.append(new_coop)
        self.current_farmer.add_coop(coop_id)
        print(f"Coop {coop_id} added to farmer {self.current_farmer.name}!")
        self.save_data()
    
    def view_my_coops(self):
        """Ver gallineros del granjero actual"""
        my_coops = [coop for coop in self.coops if coop.farmer_id == self.current_farmer.farmer_id]
        
        if not my_coops:
            print("You don't have any coops yet.")
            return
        
        print(f"\n--- My Coops - {self.current_farmer.name} ---")
        for coop in my_coops:
            print(coop)
            print("-" * 40)
    
    def chicken_management_menu(self):
        """Menú de gestión de gallinas"""
        if not self.current_farmer:
            print("Please select a farmer first!")
            return
        
        while True:
            print(f"\n--- Chicken Management - Farmer: {self.current_farmer.name} ---")
            print("1. Add chicken to coop")
            print("2. Make chicken do stuff")
            print("3. Back to main menu")
            
            choice = input("Choose an option: ").strip()
            
            if choice == '1':
                self.add_chicken_to_coop()
            elif choice == '2':
                self.make_chicken_do_stuff()
            elif choice == '3':
                break
            else:
                print("Invalid option.")
    
    def add_chicken_to_coop(self):
        """Agregar gallina a gallinero"""
        my_coops = [coop for coop in self.coops if coop.farmer_id == self.current_farmer.farmer_id]
        
        if not my_coops:
            print("You don't have any coops. Please create a coop first.")
            return
        
        print("\n--- Add Chicken to Coop ---")
        print("Your coops:")
        for coop in my_coops:
            print(f"Coop ID: {coop.coop_id} ({len(coop.chickens)} chickens)")
        
        try:
            coop_id = int(input("Enter coop ID: "))
            selected_coop = next((coop for coop in my_coops if coop.coop_id == coop_id), None)
            
            if not selected_coop:
                print("Coop not found or you don't own it!")
                return
            
            print("\n--- New Chicken Details ---")
            chicken_id = int(input("Chicken ID: "))
            name = input("Name: ").strip()
            color = input("Color: ").strip()
            age = int(input("Age: "))
            is_molting = input("Is molting? (y/n): ").strip().lower() == 'y'
            
            # Verificar si el ID de gallina ya existe en este gallinero
            if any(chicken.chicken_id == chicken_id for chicken in selected_coop.chickens):
                print("Chicken ID already exists in this coop!")
                return
            
            new_chicken = Chicken(chicken_id, name, color, age, is_molting)
            selected_coop.add_chicken(new_chicken)
            print(f"Chicken '{name}' added to coop {coop_id}!")
            self.save_data()
        
        except ValueError:
            print("Please enter valid numbers.")
    
    def make_chicken_do_stuff(self):
        """Hacer que una gallina realice acciones"""
        my_coops = [coop for coop in self.coops if coop.farmer_id == self.current_farmer.farmer_id]
        
        if not my_coops:
            print("You don't have any coops.")
            return
        
        print("\n--- Make Chicken Do Stuff ---")
        print("Your coops:")
        for coop in my_coops:
            print(f"Coop ID: {coop.coop_id} ({len(coop.chickens)} chickens)")
        
        try:
            coop_id = int(input("Enter coop ID: "))
            selected_coop = next((coop for coop in my_coops if coop.coop_id == coop_id), None)
            
            if not selected_coop:
                print("Coop not found!")
                return
            
            if not selected_coop.chickens:
                print("No chickens in this coop!")
                return
            
            print("\nChickens in this coop:")
            for chicken in selected_coop.chickens:
                print(chicken)
            
            chicken_id = int(input("Enter chicken ID: "))
            selected_chicken = next((chicken for chicken in selected_coop.chickens if chicken.chicken_id == chicken_id), None)
            
            if selected_chicken:
                print(f"\n--- {selected_chicken.name} is doing stuff ---")
                selected_chicken.do_stuff()
            else:
                print("Chicken not found!")
        
        except ValueError:
            print("Please enter valid numbers.")