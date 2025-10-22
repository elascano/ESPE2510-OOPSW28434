from Chicken import Chicken

def main():
    print(" Welcome to the Chicken Farm Simulator \n")

    chickens = []

    while True:
        print("\n--- Enter a new chicken ---")
        try:
            id = int(input("Enter the chicken ID: "))
            name = input("Enter the chicken name: ")
            color = input("Enter the chicken color: ")
            age = int(input("Enter the chicken age: "))
            is_molting_input = input("Is the chicken molting? (yes/no): ").strip().lower()
            is_molting = is_molting_input == "yes"
        except ValueError:
            print("Error: please enter valid data.")
            continue

        chicken = Chicken(id, name, color, age, is_molting)
        chickens.append(chicken)

        print("\n Chicken successfully added:")
        print(chicken)

        continue_input = input("\nDo you want to add another chicken? (yes/no): ").strip().lower()
        if continue_input != "yes":
            break

    print("\n===== List of chickens on the farm =====")
    for c in chickens:
        print(c)
        print("-" * 40)

    print("\nChickens start their activities...\n")
    for c in chickens:
        c.do_stuff()


if __name__ == "__main__":
    main()
