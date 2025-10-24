from Chicken import Chicken
from ChickenCoop import ChickenCoop

class ChickenFarmSimulator:

    def __init__(self):
        self.coops = []

    def display_welcome(self):
        print("\n  Welcome to the Chicken Farm Simulator  ")

    def setup_farm(self):
        print("\nSetting up the chicken farm...")

        chickens = [
            Chicken("Lucy", "White and Brown", 2, False),
            Chicken("Maruja", "White", 1, True),
            Chicken("Carmela", "Brown", 2, False),
            Chicken("Pepa", "Black", 3, False),
            Chicken("Mary", "Brown", 4, True),
            Chicken("Josefa", "Golden", 3, False),
            Chicken("Betty", "Black", 1, False),
            Chicken("Rita", "White and Brown", 2, False),
            Chicken("Blanca", "White", 3, True),
            Chicken("Tina", "Golden", 1, False),
        ]

        coop1 = ChickenCoop("Happy Hens Coop")
        coop2 = ChickenCoop("Sunrise Nest")

        for i in range(6):
            coop1.add_chicken(chickens[i])

        for i in range(6, 10):
            coop2.add_chicken(chickens[i])

        self.coops = [coop1, coop2]

    def display_farm_info(self):
        print("\n ---- Farm Setup Complete ---- ")
        for coop in self.coops:
            print(coop)
        print("-------------------------------")

    def show_coop_details(self, coop):
        print(f"\n--- Chickens in {coop.name} (ID: {coop.id}) ---")
        chickens = coop.get_chickens()

        if not chickens:
            print("This coop is currently empty.")
        else:
            print(f"Total Chickens: {len(chickens)}\n")
            for chicken in chickens:
                print(chicken)

    def get_valid_coop_choice(self):
        while True:
            try:
                choice = int(input("\nSelect a coop to view (1 or 2): "))
                if 1 <= choice <= len(self.coops):
                    return self.coops[choice - 1]
                else:
                    print("Invalid choice. Please enter 1 or 2.")
            except ValueError:
                print("Please enter a number.")

    def main(self):
        self.display_welcome()
        self.setup_farm()
        self.display_farm_info()

        while True:
            coop = self.get_valid_coop_choice()
            self.show_coop_details(coop)

            while True:
                again = input("\nDo you want to see another coop? (yes/no): ").strip().lower()
                if again in ["yes", "y"]:
                    break  
                elif again in ["no", "n"]:
                    print("\nExiting the Chicken Farm Simulator.")
                    return  
                else:
                    print("Invalid input. Please type 'yes' or 'no'.")



if __name__ == "__main__":
    simulator = ChickenFarmSimulator()
    simulator.main()