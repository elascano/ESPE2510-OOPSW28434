# Autor: Josue Carvajal
# Versión: 0.2

from ChickenFarmSimulator_model.Chicken import Chicken
from ChickenFarmSimulator_model.ChickenCoop import ChickenCoop

def main():
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

if __name__ == "__main__":
    main()