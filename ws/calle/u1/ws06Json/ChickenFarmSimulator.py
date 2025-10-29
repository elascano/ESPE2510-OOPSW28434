from farmer import Farmer


def menu():
    farmer = Farmer()

    while True:
        print("\n=== Chicken Farm Simulator ===")
        print("1. Add Chicken")
        print("2. Read Chickens")
        print("3. Exit")

        option = input("Choose an option: ")

        if option == "1":
            farmer.add_chicken()
        elif option == "2":
            farmer.read_chickens()
        elif option == "3":
            print(" Exiting the simulator...")
            break
        else:
            print(" Invalid option.")

if __name__ == "__main__":
    menu()
