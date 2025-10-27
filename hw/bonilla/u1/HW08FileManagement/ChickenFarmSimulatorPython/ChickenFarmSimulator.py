from Chicken import Chicken
from ChickenCoop import ChickenCoop

class ChickenFarmSimulator:

    def __init__(self):
        self.coops = []

    def display_welcome(self):
        print("\n  Welcome to the Chicken Farm Simulator  ")

    def setup_farm(self):
        print("\nSetting up the chicken farm...")

        coops_dict = {}

        with open("ChickenFarmData.csv", "r") as file:
            next(file)
            for line in file:
                line = line.strip()
                if not line:
                    continue
                parts = line.split(",")
                chicken_id = int(parts[0])
                name = parts[1]
                color = parts[2]
                age = int(parts[3])
                is_molting = parts[4].strip().lower() == "true"
                coop_name = parts[5]

                chicken = Chicken(name, color, age, is_molting, chicken_id)

                if coop_name not in coops_dict:
                    coops_dict[coop_name] = ChickenCoop(coop_name)
                coops_dict[coop_name].add_chicken(chicken)

        self.coops = list(coops_dict.values())

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
                    print(f"Invalid choice. Please enter a number between 1 and {len(self.coops)}.")
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