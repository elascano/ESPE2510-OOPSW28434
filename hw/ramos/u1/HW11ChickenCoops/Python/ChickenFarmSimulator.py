from Chicken import Chicken
from ChickenCoop import ChickenCoop

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


if __name__ == "__main__":
    main()
