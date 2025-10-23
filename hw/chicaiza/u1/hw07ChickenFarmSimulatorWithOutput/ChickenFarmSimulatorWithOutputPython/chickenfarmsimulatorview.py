from Chicken import Chicken

chickens = []

def show_chicken(chicken):
    print(chicken)

def show_all_chickens():
    if not chickens:
        print("No chickens registered.")
        return
    print("--- Chicken List ---")
    for ch in chickens:
        show_chicken(ch)

def add_chicken():
    name = input("Chicken name: ")
    age = int(input("Age: "))
    color = input("Color: ")
    molting_input = input("Is the chicken molting? (yes/no): ").lower()
    is_molting = molting_input == "yes"

    chicken = Chicken(name, color, age, is_molting)
    chickens.append(chicken)
    print("Chicken added successfully.")

def main_menu():
    while True:
        print("\n===== CHICKEN FARM MENU =====")
        print("1. Add new chicken")
        print("2. Show all chickens")
        print("0. Exit")
        try:
            option = int(input("Select an option: "))
        except ValueError:
            print("Invalid input. Please enter a number.")
            continue

        if option == 1:
            add_chicken()
        elif option == 2:
            show_all_chickens()
        elif option == 0:
            print("Exiting...")
            break
        else:
            print("Invalid option. Try again.")

if __name__ == "__main__":
    main_menu()