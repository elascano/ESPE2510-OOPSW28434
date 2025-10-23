import random
from chicken import Chicken

def main():
    print("Welcome to the Chicken Farm Simulator\n")

    colors = ("White", "Brown", "Black", "Yellow", "Gray")
    names = ("Lucy", "Maruja", "Rosita", "Clara", "Pepe")

    chicken1 = Chicken(
        1,
        random.choice(names),                    
        random.choice(colors),                   
        random.randint(1, 5),                    
        random.choice([True, False])            
    )

    chicken2 = Chicken(
        2,
        random.choice(names),
        random.choice(colors),
        random.randint(1, 5),
        random.choice([True, False])
    )

    chicken3 = Chicken(
        3,
        random.choice(names),
        random.choice(colors),
        random.randint(1, 5),
        random.choice([True, False])
    )

    while True:
        print("\n--- Chicken Farm Menu ---")
        print("1. View chicken characteristics")
        print("2. Make a chicken do its routine")
        print("3. Exit")

        option = input("Choose an option (1-3): ")

        if option == "1":
            print("\nWhich chicken do you want to see?")
            print(f"1. {chicken1._name}")
            print(f"2. {chicken2._name}")
            print(f"3. {chicken3._name}")

            choice = input("Enter the number of the chicken: ")

            if choice == "1":
                print(chicken1)
            elif choice == "2":
                print(chicken2)
            elif choice == "3":
                print(chicken3)
            else:
                print("Invalid option.")

        elif option == "2":
            print("\nWhich chicken should do its routine?")
            print(f"1. {chicken1._name}")
            print(f"2. {chicken2._name}")
            print(f"3. {chicken3._name}")

            choice = input("Enter the number of the chicken: ")

            if choice == "1":
                chicken1.do_stuff()
            elif choice == "2":
                chicken2.do_stuff()
            elif choice == "3":
                chicken3.do_stuff()
            else:
                print("Invalid option.")

        elif option == "3":
            print("Exiting...")
            break

        else:
            print("Please choose a valid option (1-3).")

if __name__ == "__main__":
    main()