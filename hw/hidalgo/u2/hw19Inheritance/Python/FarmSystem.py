from Cage import Cage
from Location import Location
from Pig import Pig

class FarmSystem:
    def __init__(self):
        self.cages = []

    def add_cage(self, cage):
        self.cages.append(cage)

    def list_cages(self):
        return [cage.cage_id for cage in self.cages]


# ---- Ejemplo de uso ----
if __name__ == "__main__":

    # Crear ubicación
    loc1 = Location("North Field", "A3")

    # Crear jaula
    cage1 = Cage("C-101", 2, loc1)

    # Crear animales
    pig1 = Pig("Porky", 3, 150, "Yorkshire")
    pig2 = Pig("Rosita", 2, 120, "Hampshire")

    # Añadir animales
    print(cage1.add_animal(pig1))
    print(cage1.add_animal(pig2))

    # Crear sistema y añadir jaula
    farm = FarmSystem()
    farm.add_cage(cage1)

    # Mostrar datos
    print("Animales en la jaula:", cage1.list_animals())
    print("Cages en la granja:", farm.list_cages())
