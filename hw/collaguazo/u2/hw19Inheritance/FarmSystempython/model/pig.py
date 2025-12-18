from model.farm_animal import FarmAnimal

class Pig(FarmAnimal):
    def __str__(self):
        return f"Pig({super().__str__()})"
