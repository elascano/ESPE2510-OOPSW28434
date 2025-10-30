import json
from farmer import Farmer
from coop import Coop
from chicken import Chicken

FILE_PATH = "farm_data.json"

# ===================== FUNCIONES JSON =====================

def save_to_json(farmer):
    with open(FILE_PATH, "w") as f:
        json.dump(farmer.to_dict(), f, indent=4)
    print(f"\n Data saved successfully to {FILE_PATH}\n")

def read_from_json():
    try:
        with open(FILE_PATH, "r") as f:
            data = json.load(f)
            return Farmer.from_dict(data)
    except FileNotFoundError:
        print("\n No saved data found. Please create a new farm first.\n")
        return None

def show_json_file():
    """Lee y muestra el contenido del archivo JSON"""
    try:
        with open(FILE_PATH, "r") as f:
            data = json.load(f)
            print("\n === FARM DATA (JSON) === \n")
            print(json.dumps(data, indent=4))
            print("\n============================\n")
    except FileNotFoundError:
        print("\n No JSON file found. Please create a farm first.\n")

# ===================== OPCION 1: CREAR GRANJA =====================

def create_farm():
    print("\n CREATE NEW FARM ")

    name = input("Enter farmer name: ")
    age = int(input("Enter farmer age: "))
    gender = input("Enter farmer gender: ")

    farmer = Farmer(name, age, gender)

    n_coops = int(input("\nHow many coops do you want to create? "))
    for i in range(1, n_coops + 1):
        coop_name = input(f"Enter name for coop #{i}: ")
        coop = Coop(coop_name)

        n_chickens = int(input(f"How many chickens in coop '{coop_name}'? "))
        for j in range(1, n_chickens + 1):
            chicken = Chicken.create_from_input(j)
            coop.add_chicken(chicken)

        farmer.add_coop(coop)

    farmer.review_coops()
    save_to_json(farmer)

# ===================== OPCION 2: ADMINISTRAR GRANJA =====================

def manage_farm():
    print("\n LOAD EXISTING FARM ")

    farmer = read_from_json()
    if not farmer:
        return

    farmer.review_coops()

    manage_more = "yes"
    while manage_more == "yes":
        farmer.manage_coop()
        save_to_json(farmer)
        manage_more = input("\nDo you want to manage another coop? (yes/no): ").lower()

# ===================== MENU PRINCIPAL =====================

def main():
    while True:
        print("\n==========  CHICKEN FARM MENU  ==========")
        print("1. Create new farm and register chickens")
        print("2. Load existing farm and manage chickens")
        print("3. View farm_data.json file")
        print("4. Exit program")
        print("==========================================")

        choice = input("Select an option (1-4): ")

        if choice == "1":
            create_farm()
        elif choice == "2":
            manage_farm()
        elif choice == "3":
            show_json_file()
        elif choice == "4":
            print("\n Exiting the Chicken Farm Simulator. Goodbye!\n")
            break
        else:
            print("\n Invalid option. Please try again.\n")

if __name__ == "__main__":
    main()
