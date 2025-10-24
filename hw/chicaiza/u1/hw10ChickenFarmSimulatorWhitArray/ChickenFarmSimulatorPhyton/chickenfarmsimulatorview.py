from Chicken import Chicken
from Coop import Coop

def main():
    # Crear 10 gallinas (estáticas)
    chickens = [
        Chicken("Lola", "Brown", 2, False),
        Chicken("Pepa", "White", 1, True),
        Chicken("Cuca", "Black", 3, False),
        Chicken("Tita", "Gray", 2, True),
        Chicken("Chispa", "White", 1, False),
        Chicken("Luna", "Brown", 4, True),
        Chicken("Rosita", "Gray", 2, False),
        Chicken("Clara", "White", 3, False),
        Chicken("Mimi", "Black", 2, True),
        Chicken("Coco", "Brown", 1, False)
    ]

    # Crear dos gallineros
    coop1 = Coop("Coop North")
    coop2 = Coop("Coop South")

    # Asignar gallinas a cada gallinero
    for i in range(5):
        coop1.add_chicken(chickens[i])
    for i in range(5, 10):
        coop2.add_chicken(chickens[i])

    # Mostrar gallinas por gallinero
    coop1.show_chickens()
    coop2.show_chickens()

if __name__ == "__main__":
    main()