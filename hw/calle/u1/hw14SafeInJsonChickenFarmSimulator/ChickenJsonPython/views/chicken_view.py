from typing import List
from models.chicken import Chicken

class ChickenView:
    @staticmethod
    def show_menu():
        print("\n=== CHICKEN MANAGEMENT SYSTEM ===")
        print("1. Insert chicken")
        print("2. List chickens")
        print("3. Delete chicken")
        print("4. Update chicken")
        print("5. Find chickens")
        print("6. Exit")

    @staticmethod
    def get_menu_choice() -> int:
        try:
            return int(input("Select an option: "))
        except ValueError:
            return -1
    
    @staticmethod
    def get_chicken_data(suggested_id: int = None):
        if suggested_id:
            print(f"Suggested ID: {suggested_id}")
        
        try:
            id = int(input("Chicken ID: ") or suggested_id)
            name = input("Name: ")
            color = input("Color: ")
            age = int(input("Age: "))
            is_molting_input = input("Is molting? ").lower()
            is_molting = is_molting_input == 'true'
            
            return id, name, color, age, is_molting
        except ValueError:
            print("Error: Enter valid data")
            return None
    
    @staticmethod
    def show_chickens(chickens: List[Chicken]):
        if not chickens:
            print("No chickens registered.")
            return

        print("\n=== LIST OF CHICKENS ===")
        # Print table header
        print(f"{'ID':<5} {'Name':<20} {'Color':<10} {'Age':<5} {'Is Molting':<10}")
        print("-" * 50)
        for chicken in chickens:
            print(f"{chicken.id:<5} {chicken.name:<20} {chicken.color:<10} {chicken.age:<5} {'Si' if chicken.is_molting else 'No':<10}")
        print(f"\nTotal: {len(chickens)} chickens")

    @staticmethod
    def get_chicken_id() -> int:
        try:
            return int(input("Chicken ID: "))
        except ValueError:
            return -1
    
    @staticmethod
    def get_search_name() -> str:
        return input("Name to search: ")

    @staticmethod
    def show_message(message: str):
        print(message)
    
    @staticmethod
    def show_error(message: str):
        print(f"Error: {message}")