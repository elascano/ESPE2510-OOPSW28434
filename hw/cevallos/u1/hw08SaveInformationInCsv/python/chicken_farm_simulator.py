from chicken import Chicken
from chicken_coop import ChickenCoop

def main():
    print(" Welcome to the Chicken Farm Simulator ")

    
    coop_id = int(input("\nEnter Coop ID: "))
    description = input("Enter Coop description: ")
    coop = ChickenCoop(coop_id, description)

    
    while True:
        print("\n--- Enter chicken data ---")
        id = int(input("ID: "))
        name = input("Name: ")
        color = input("Color: ")
        age = int(input("Age: "))
        is_molting_input = input("Is molting? (yes/no): ").lower()
        is_molting = is_molting_input == "yes"

        
        chicken = Chicken(id, name, color, age, is_molting)
        coop.add_chicken(chicken)
        chicken.save_to_csv()

        more = input("Add another chicken? (y/n): ").lower()
        if more != "y":
            break

    
    print("\n--- Coop Summary ---")
    print(coop)
    coop.list_chickens()

    
    print("\n--- Starting chicken simulation ---")
    coop.make_all_do_stuff()

    print("\nSimulation complete. Data saved in chickens.csv ")

if __name__ == "__main__":
    main()
