from model.chicken import Chicken
from model.chicken_coop import ChickenCoop
import csv

def main():
    print("🐔 Welcome to the Chicken Farm Simulator 🏡\n")


    chickens = [
        Chicken(1, "Lucy", "black", 2, False),
        Chicken(2, "Maruja", "white", 1, True),
        Chicken(3, "Carmela", "brown", 3, False),
        Chicken(4, "Pepa", "red", 2, False),
        Chicken(5, "Nina", "yellow", 1, True),
        Chicken(6, "Tita", "gray", 4, False),
        Chicken(7, "Rosa", "white", 2, False),
        Chicken(8, "Lola", "black", 1, False),
        Chicken(9, "Mimi", "orange", 3, True),
        Chicken(10, "Chispa", "brown", 2, False),
    ]


    coop1 = ChickenCoop(101)
    coop2 = ChickenCoop(102)

    # Asignar algunos pollos al gallinero 1 y otros al 2
    for i, chicken in enumerate(chickens):
        if i < 5:
            coop1.add(chicken)
        else:
            coop2.add(chicken)


    print("Coop 1 contents:")
    coop1.show_all_chickens()
    print("\nCoop 2 contents:")
    coop2.show_all_chickens()


    save_to_csv("chickens_data.csv", [coop1, coop2])

    print("\nData saved to 'chickens_data.csv' successfully!")


    print("\nExample actions:")
    chickens[0].do_stuff()
    chickens[3].do_stuff()
    chickens[7].do_stuff()


def save_to_csv(filename, coops):
    """Guarda la información de todos los gallineros en un archivo CSV."""
    with open(filename, mode="w", newline="", encoding="utf-8") as file:
        writer = csv.writer(file)
        writer.writerow(["Coop ID", "Chicken ID", "Name", "Color", "Age", "Is Molting"])
        for coop in coops:
            writer.writerows(coop.to_list())


if __name__ == "__main__":
    main()
