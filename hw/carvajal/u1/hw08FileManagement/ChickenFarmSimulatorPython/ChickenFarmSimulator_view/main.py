# Autor: Josue Carvajal
# Versión: 0.2

import csv
import sys
from io import StringIO

from ChickenFarmSimulator_model.Chicken import Chicken
from ChickenFarmSimulator_model.ChickenCoop import ChickenCoop

def main():
    # Capturar la salida impresa
    output_capture = StringIO()
    sys.stdout = output_capture

    print("-----Welcome to the Chicken Farm Simulator -----")

    # Creamos las gallinas
    chickens = [
        Chicken(1, "Lucy", "White", 2, False),
        Chicken(2, "Maruja", "Brown", 1, True),
        Chicken(3, "Rosita", "Black", 3, False),
        Chicken(4, "Lola", "White and Brown", 2, True),
        Chicken(5, "Clara", "Yellow", 1, False),
        Chicken(6, "Rita", "Red", 2, False),
        Chicken(7, "Sofi", "Black", 3, True),
        Chicken(8, "Pepa", "White", 2, False),
        Chicken(9, "Nina", "Gray", 1, True),
        Chicken(10, "Luna", "Golden", 4, False),
    ]

    # Creamos los gallineros
    coop1 = ChickenCoop(1)
    coop2 = ChickenCoop(2)

    # Asignamos gallinas a cada gallinero
    for i in range(5):
        coop1.add_chicken(chickens[i])
    for i in range(5, 10):
        coop2.add_chicken(chickens[i])

    # Mostramos resultados
    coop1.show_chickens()
    coop2.show_chickens()

    # 🔽 Guardar resultados en archivos 🔽

    # Restaurar salida normal y obtener texto
    sys.stdout = sys.__stdout__
    full_output = output_capture.getvalue()

    # Guardar en TXT
    with open("chickens_report.txt", "w", encoding="utf-8") as txt_file:
        txt_file.write(full_output)

    # Guardar en CSV
    with open("chickens_report.csv", "w", newline="", encoding="utf-8") as csv_file:
        writer = csv.writer(csv_file)
        writer.writerow(["Coop ID", "Chicken ID", "Name", "Color", "Age", "Is Molting"])
        for c in coop1.chickens:
            writer.writerow([coop1.id, c.id, c.name, c.color, c.age, c.is_molting])
        for c in coop2.chickens:
            writer.writerow([coop2.id, c.id, c.name, c.color, c.age, c.is_molting])

    print("\n✅ Data saved to 'chickens_report.txt' and 'chickens_report.csv' successfully!")

if __name__ == "__main__":
    main()