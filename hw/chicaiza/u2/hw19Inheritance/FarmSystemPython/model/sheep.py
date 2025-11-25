from datetime import date
from model.farm_animal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, last_sheering: date, id, breed, born_on, gender,
                 is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.last_sheering = last_sheering

    def cut_wool(self):
        self.last_sheering = date.today()

    def __str__(self):
        return f"Sheep(lastSheering={self.last_sheering}, {super().__str__()})"
