from model.farmAnimal import FarmAnimal
from datetime import date

class Sheep(FarmAnimal):
    def __init__(self, lastSheering, id, breed, bornOn, gender, isAbleToReproduce, weight, cage):
        super().__init__(id, breed, bornOn, gender, isAbleToReproduce, weight, cage)
        self.lastSheering = lastSheering

    def shear(self):
        self.lastSheering = date.today()

    def __str__(self):
        return (
            f"=== SHEEP ===\n"
            f"Last Sheering: {self.lastSheering}\n"
            f"{super().__str__()}\n"
            f"============================"
        )
