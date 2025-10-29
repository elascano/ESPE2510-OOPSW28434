from model.chicken import Chicken
from model.chicken_coop import ChickenCoop
from model.farmer import Farmer
import json
import os

def main():
    print("Welcome to the Chicken Farm Simulator \n")

    farmer_name = input("Enter the farmer's name: ")
    farmer = Farmer(farmer_name)

    # Create chicken coops
    num_coops = int(input("How many chicken coops does the farmer have? "))

    for i in range(num_coops):
        print(f"\n--- Chicken Coop #{i+1} ---")
        coop_id = int(input("Enter the chicken coop ID: "))
        coop = ChickenCoop(coop_id)

        num_chickens = int(input(f"How many chickens are in coop {coop_id}? "))

        for j in range(num_chickens):
            print(f"\nChicken {j+1} data:")
            name = input("  Name: ")
            color = input("  Color: ")
            age = int(input("  Age: "))
            is_molting_input = input("  Is it molting feathers? (yes/no): ").strip().lower()
            is_molting = is_molting_input == "yes"

            chicken = Chicken(j+1, name, color, age, is_molting)
            coop.add(chicken)

        farmer.add_coop(coop)

    # Summary
    print("\n FARM SUMMARY:")
    print(f"Farmer: {farmer.name}")
    print(f"Total coops: {farmer.count_coops()}")
    print(f"Total chickens: {farmer.count_total_chickens()} ")

    for coop in farmer.coops:
        print(f" - Coop {coop.id} has {coop.count_chickens()} chickens")

    # Save data to JSON (backup)
    os.makedirs("data", exist_ok=True)
    save_to_json("data/chickens_data.json", farmer)
    print("\n Data successfully saved to JSON!")

    # ===== INTERACTIVE MENU =====
    while True:
        print("\n MENU OPTIONS")
        print("1. View chickens in a coop")
        print("2. Exit")
        choice = input("Select an option (1-2): ").strip()

        if choice == "1":
            try:
                coop_id = int(input("Enter the ID of the coop you want to view: "))
                coop_found = next((coop for coop in farmer.coops if coop.id == coop_id), None)
                if coop_found:
                    print(f"\n Coop {coop_found.id} has {coop_found.count_chickens()} chickens:\n")
                    for chicken in coop_found.chickens:
                        print(f"  - Name: {chicken.name}, Color: {chicken.color}, Age: {chicken.age}, Molting: {chicken.is_molting}")
                        chicken.do_stuff()
                else:
                    print(" Coop not found. Please try again.")
            except ValueError:
                print(" Invalid input. Please enter a number.")
        elif choice == "2":
            print("\n Exiting the farm simulator. Goodbye!")
            break
        else:
            print(" Invalid option. Choose 1 or 2.")

def save_to_json(filename, farmer):
    """Saves all the farmer and coop information."""
    data = {
        "farmer": farmer.name,
        "coops": [coop.to_dict() for coop in farmer.coops]
    }
    with open(filename, "w", encoding="utf-8") as file:
        json.dump(data, file, indent=4)

if __name__ == "__main__":
    main()

