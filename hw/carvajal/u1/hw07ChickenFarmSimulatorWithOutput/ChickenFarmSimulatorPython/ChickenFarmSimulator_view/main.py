from ChickenFarmSimulator_model.Chicken import Chicken

def show_menu():
    print("\n--- Chicken Farm Menu ---")
    print("1. View chicken characteristics")
    print("2. Make a chicken do its routine")
    print("3. Exit")

def select_chicken(chickens):
    print("\nWhich chicken do you want to select?")
    for i, chicken in enumerate(chickens, start=1):
        print(f"{i}. {chicken.name}")
    choice = int(input("Enter the number of the chicken: "))
    return chickens[choice - 1]

def main():
    print("Welcome to the Chicken Farm Simulator")

    # Creamos las gallinas
    chickens = [
        Chicken(1, "Lucy", "White"),
        Chicken(2, "Clara", "Brown"),
        Chicken(3, "Maria", "Black")
    ]

    while True:
        show_menu()
        option = input("Choose an option (1-3): ")

        if option == "1":
            chicken = select_chicken(chickens)
            chicken.show_data()

        elif option == "2":
            chicken = select_chicken(chickens)
            chicken.do_routine()

        elif option == "3":
            print("Goodbye! 🐔")
            break

        else:
            print("Invalid option. Please try again.")

if __name__ == "__main__":
    main()