from chicken import Chicken

print(" Welcome to the Chicken Farm Simulator!\n")

chickens = []

while True:
    try:
        id = int(input("Enter chicken id (int): "))
        name = input("Enter chicken name: ")
        color = input("Enter chicken color: ")
        age = int(input("Enter chicken age (int): "))
        is_molting_input = input("Is molting? (yes/no): ").strip().lower()
        is_molting = True if is_molting_input == "yes" else False

        chicken = Chicken(id, name, color, age, is_molting)
        chickens.append(chicken)

        print("\n Chicken added successfully!")
        print(chicken)

        print("\n Let's see what this chicken does...")
        chicken.do_stuff()

        again = input("\nDo you want to add another chicken? (yes/no): ").strip().lower()
        if again != "yes":
            break

    except ValueError:
        print(" Invalid input. Please try again.\n")

print("\n Summary of chickens in the farm:")
for c in chickens:
    print(c)

print("\nSimulation complete! ")
