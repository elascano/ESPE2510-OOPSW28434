from coop import Coop
from chicken import Chicken
import csv

def main():
    farm = []

    num_coops = int(input("How many coops does the farm have?: "))

    for i in range(num_coops):
        coop_name = input(f"Enter name for coop {i + 1}: ")
        coop = Coop(coop_name)

        num_chickens = int(input(f"How many chickens are in {coop_name}?: "))

        for j in range(num_chickens):
            chicken_name = input(f"Enter name for chicken {j + 1}: ")
            chicken = Chicken(chicken_name)
            coop.add_chicken(chicken)

        farm.append(coop)

    print("\n=== FARM STRUCTURE ===")
    for coop in farm:
        print(coop)

    save_to_csv(farm)
    print("\n✅ CSV file created successfully: farm.csv")


def save_to_csv(farm):
    """Saves the farm structure into a CSV file"""
    with open("farm.csv", mode="w", newline="", encoding="utf-8") as file:
        writer = csv.writer(file)
        writer.writerow(["Coop", "Chicken"])  # CSV header

        for coop in farm:
            for chicken in coop.get_chickens():
                writer.writerow([coop.get_coop_name(), chicken.get_name()])


if __name__ == "__main__":
    main()
