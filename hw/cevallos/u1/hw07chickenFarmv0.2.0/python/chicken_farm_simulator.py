from chicken import Chicken

print("Welcome to the Chicken Farm Simulator")

chickens = []

while True:
    print("\n--- Enter chicken data ---")
    id = int(input("ID: "))
    name = input("Name: ")
    color = input("Color: ")
    age = int(input("Age: "))
    is_molting_input = input("Is molting? (yes/no): ").lower()
    is_molting = is_molting_input == "yes"

    chicken = Chicken(id, name, color, age, is_molting)
    chickens.append(chicken)
    chicken.save_to_csv()

    more = input("Add another chicken? (y/n): ").lower()
    if more != "y":
        break

print("\nRunning chicken simulation...\n")
for c in chickens:
    print(c)
    c.do_stuff()

print("\nSimulation complete. Data saved in chickens.csv")
