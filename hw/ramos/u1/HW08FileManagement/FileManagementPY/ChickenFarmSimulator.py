from Chicken import Chicken
from ChickenCoop import ChickenCoop
import csv

def main():
    print(" ---Chicken Farm Simulator--- \n")

    # Crear gallinas (datos estáticos)
    chickens = [
        Chicken(1, "Lucy", "White", 2, False),
        Chicken(2, "Nita", "Gray", 1, True),
        Chicken(3, "Lola", "Black", 3, False),
        Chicken(4, "Pepa", "White", 2, True),
        Chicken(5, "Clara", "Gray", 1, False),
        Chicken(6, "Rita", "Brown", 4, True),
        Chicken(7, "Tina", "Gray", 2, False),
        Chicken(8, "Sofi", "Red", 3, True),
        Chicken(9, "Lili", "White", 2, False),
        Chicken(10, "Dani", "White", 1, True),
    ]

    # Crear gallineros
    coop1 = ChickenCoop(1)
    coop2 = ChickenCoop(2)

    # Asignar gallinas
    for chicken in chickens[:5]:
        coop1.add_chicken(chicken)

    for chicken in chickens[5:]:
        coop2.add_chicken(chicken)

    # Mostrar resultados
    print(coop1)
    print()
    print(coop2)

    # GUARDAR EN ARCHIVO CSV
    with open("chickens_data.csv", mode="w", newline="", encoding="utf-8") as file:
        writer = csv.writer(file)
        writer.writerow(["ID", "NAME", "COLOR", "AGE", "IS_MOLTING"])
        for chicken in chickens:
            writer.writerow([chicken.get_id(), chicken.get_name(), chicken.get_color(), chicken.get_age(),chicken.is_molting()])


    print("\nDatos guardados correctamente en 'chickens_data.csv'")

    # GUARDAR EN ARCHIVO TXT
    with open("chickens_data.txt", mode="w", encoding="utf-8") as file:
        file.write("CHICKEN INFORMATION\n")
        file.write("===================\n\n")
        for chicken in chickens:
            file.write(f"ID: {chicken.get_id()}\n")
            file.write(f"NAME: {chicken.get_name()}\n")
            file.write(f"COLOR: {chicken.get_color()}\n")
            file.write(f"AGE: {chicken.get_age()}\n")
            file.write(f"IS MOLTING: {chicken.is_molting()}\n")

    print("Datos guardados correctamente en 'chickens_data.txt'")


if __name__ == "__main__":
    main()
