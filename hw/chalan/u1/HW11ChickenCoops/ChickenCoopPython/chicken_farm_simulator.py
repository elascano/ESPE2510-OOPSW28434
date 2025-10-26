from model.chicken import Chicken
from model.chicken_coop import ChickenCoop
import csv

def main():
    print("Farm Simulation by Kevin Chalan\n")


    chickens = [
        Chicken(1, "katya", "black", 2, False),
        Chicken(2, "Mari", "white", 1, True),
        Chicken(3, "Carolina", "brown", 3, False),
        Chicken(4, "Pin", "red", 2, False),
        Chicken(5, "Narcisa", "yellow", 1, True),
        Chicken(6, "Tita", "gray", 4, False),
        Chicken(7, "Rosita", "white", 2, False),
        Chicken(8, "Lolita", "black", 1, False),
        Chicken(9, "wasp", "orange", 3, True),
        Chicken(10, "Cherita", "brown", 2, False),
    ]


    coop1 = ChickenCoop(101)
    coop2 = ChickenCoop(102)

    for i, chicken in enumerate(chickens):
        if i < 5:
            coop1.add(chicken)
        else:
            coop2.add(chicken)


    print("COOP ONE")
    coop1.show_all_chickens()
    print("\nCOOP TWO")
    coop2.show_all_chickens()


    save_to_csv("chickensData.csv", [coop1, coop2])

    print("\n 'chickensData.csv' is saving!")


    print("\nExample actions:")
    chickens[0].do_stuff()
    chickens[3].do_stuff()
    chickens[7].do_stuff()


def save_to_csv(filename, coops):
    """Se guarda en Excel."""
    with open(filename, mode="w", newline="", encoding="utf-8") as file:
        writer = csv.writer(file)
        writer.writerow(["Coop ID", "Chicken ID", "Name", "Color", "Age", "Is Molting"])
        for coop in coops:
            writer.writerows(coop.to_list())


if __name__ == "__main__":
    main()
