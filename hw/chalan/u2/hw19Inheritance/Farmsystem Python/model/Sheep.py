from datetime import datetime
from model.FarmAnimal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, last_shearing, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.last_shearing = last_shearing

    def shear(self):
        self.last_shearing = datetime.now()

    def __str__(self):
        return f"Sheep{{last_shearing={self.last_shearing}, parent={super().__str__()}}}"