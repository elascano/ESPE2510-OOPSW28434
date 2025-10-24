import random
from chicken import Chicken
from chickenCoops import ChickenCoop

def main():
    print("Welcome to the Chicken Farm Simulator\n")
    
    coop1 = ChickenCoop("Main Coop")
    coop2 = ChickenCoop("Secondary Coop")
    coops = [coop1, coop2]
    
    print("=== Creating 10 Chickens ===")
    chickens = create_multiple_chickens(10)
    
    assign_chickens_to_coops(chickens, coops)
    
    while True:
        print("\n--- Chicken Farm Menu ---")
        print("1. View all coops")
        print("2. View chickens in a specific coop")
        print("3. Make a chicken do its routine")
        print("4. Move chicken between coops")
        print("5. Add new chicken")
        print("6. Remove chicken")
        print("7. Exit")
        
        option = input("Choose an option (1-7): ")
        
        if option == "1":
            display_all_coops(coops)
            
        elif option == "2":
            display_all_coops(coops)
            try:
                coop_choice = int(input("Enter coop number: ")) - 1
                if 0 <= coop_choice < len(coops):
                    coops[coop_choice].display_chickens()
                else:
                    print("Invalid coop number.")
            except ValueError:
                print("Please enter a valid number.")
                
        elif option == "3":
            display_all_chickens(coops)
            try:
                chicken_id = int(input("Enter chicken ID to perform routine: "))
                chicken = find_chicken_by_id(chicken_id, coops)
                if chicken:
                    chicken.do_stuff()
                else:
                    print(f"Chicken with ID {chicken_id} not found.")
            except ValueError:
                print("Please enter a valid ID number.")
                
        elif option == "4":
            move_chicken_between_coops(coops)
            
        elif option == "5":
            add_new_chicken(coops)
            
        elif option == "6":
            remove_chicken_from_coop(coops)
            
        elif option == "7":
            print("Exiting...")
            break
            
        else:
            print("Please choose a valid option (1-7).")

def create_multiple_chickens(amount):
    """Crea múltiples gallinas automáticamente"""
    chickens = []
    names = ["Lucy", "Maruja", "Rosita", "Clara", "Pepe", "Lola", "Pepa", "Blanca", "Negra", "Amarilla"]
    colors = ["White", "Brown", "Black", "Yellow", "Gray", "Red", "Orange"]
    
    for i in range(1, amount + 1):
        name = random.choice(names)
        color = random.choice(colors)
        age = random.randint(1, 5)
        is_molting = random.choice([True, False])
        
        chicken = Chicken(i, name, color, age, is_molting)
        chickens.append(chicken)
        print(f"Created Chicken {i}: {name} ({color}, {age} years, molting: {is_molting})")
    
    return chickens

def assign_chickens_to_coops(chickens, coops):
    """Asigna gallinas automáticamente a los gallineros"""
    print("\n=== Assigning Chickens to Coops ===")
    for i, chicken in enumerate(chickens):
        if i < 6:
            coops[0].add_chicken(chicken)
        else:
            coops[1].add_chicken(chicken)

def display_all_coops(coops):
    """Muestra todos los gallineros"""
    print("\n--- All Chicken Coops ---")
    for i, coop in enumerate(coops, 1):
        print(f"{i}. {coop}")

def display_all_chickens(coops):
    """Muestra todas las gallinas de todos los gallineros"""
    print("\n--- All Chickens in Farm ---")
    all_chickens = []
    for coop in coops:
        all_chickens.extend(coop.get_all_chickens())
    
    if not all_chickens:
        print("No chickens in the farm.")
    else:
        for chicken in all_chickens:
            print(f"ID: {chicken.id}, Name: {chicken.name}, Color: {chicken.color}")

def find_chicken_by_id(chicken_id, coops):
    """Busca una gallina por ID en todos los gallineros"""
    for coop in coops:
        chicken = coop.get_chicken(chicken_id)
        if chicken:
            return chicken
    return None

def move_chicken_between_coops(coops):
    """Mueve una gallina de un gallinero a otro"""
    display_all_coops(coops)
    
    try:
        source_coop_num = int(input("Enter source coop number: ")) - 1
        if not (0 <= source_coop_num < len(coops)):
            print("Invalid source coop number.")
            return
            
        source_coop = coops[source_coop_num]
        if source_coop.is_empty():
            print("Source coop is empty.")
            return
            
        source_coop.display_chickens()
        chicken_id = int(input("Enter chicken ID to move: "))
        chicken = source_coop.get_chicken(chicken_id)
        
        if not chicken:
            print(f"Chicken with ID {chicken_id} not found in source coop.")
            return
            
        display_all_coops(coops)
        target_coop_num = int(input("Enter target coop number: ")) - 1
        if not (0 <= target_coop_num < len(coops)) or target_coop_num == source_coop_num:
            print("Invalid target coop number.")
            return
    
        source_coop.remove_chicken(chicken_id)
        coops[target_coop_num].add_chicken(chicken)
        print(f"Chicken '{chicken.name}' moved from {source_coop} to {coops[target_coop_num]}")
        
    except ValueError:
        print("Please enter valid numbers.")

def add_new_chicken(coops):
    """Agrega una nueva gallina a un gallinero"""
    display_all_coops(coops)
    
    try:
        coop_choice = int(input("Enter coop number to add chicken: ")) - 1
        if not (0 <= coop_choice < len(coops)):
            print("Invalid coop number.")
            return
            
        all_chickens = []
        for coop in coops:
            all_chickens.extend(coop.get_all_chickens())
        next_id = max([chicken.id for chicken in all_chickens], default=0) + 1
        
        chicken = create_chicken_from_input(next_id)
        coops[coop_choice].add_chicken(chicken)
        
    except ValueError:
        print("Please enter a valid number.")

def remove_chicken_from_coop(coops):
    """Elimina una gallina de un gallinero"""
    display_all_coops(coops)
    
    try:
        coop_choice = int(input("Enter coop number: ")) - 1
        if not (0 <= coop_choice < len(coops)):
            print("Invalid coop number.")
            return
            
        coop = coops[coop_choice]
        if coop.is_empty():
            print("Coop is empty.")
            return
            
        coop.display_chickens()
        chicken_id = int(input("Enter chicken ID to remove: "))
        coop.remove_chicken(chicken_id)
        
    except ValueError:
        print("Please enter valid numbers.")

def create_chicken_from_input(chicken_id):
    """Crea una gallina con datos ingresados por el usuario"""
    print(f"\n--- Creating Chicken {chicken_id} ---")
    
    while True:
        name = input(f"Enter name for chicken {chicken_id}: ").strip()
        if name.replace(" ", "").isalpha():
            break
        else:
            print("Please enter only letters (no numbers or special characters).")
    
    color = input(f"Enter color for chicken {chicken_id}: ")
    
    while True:
        try:
            age = int(input(f"Enter age for chicken {chicken_id}: "))
            break
        except ValueError:
            print("Please enter a valid integer for age.")
    
    while True:
        molting_input = input(f"Is chicken {chicken_id} molting? (yes/no): ").lower()
        if molting_input in ['yes', 'y', 'true', '1']:
            is_molting = True
            break
        elif molting_input in ['no', 'n', 'false', '0']:
            is_molting = False
            break
        else:
            print("Please enter 'yes' or 'no'.")
    
    return Chicken(chicken_id, name, color, age, is_molting)

if __name__ == "__main__":
    main()