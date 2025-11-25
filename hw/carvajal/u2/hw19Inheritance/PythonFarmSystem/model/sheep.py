from datetime import datetime
from model.farm_animal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, last_shearing: datetime, animal_id: int, breed: str,
                 born_on: datetime, gender: str, is_able_to_reproduce: bool,
                 weight: float, cage):
        super().__init__(animal_id, breed, born_on, gender,
                         is_able_to_reproduce, weight, cage)
        self.last_shearing = last_shearing

    def cut_wool(self):
        self.last_shearing = datetime.now()

    def shear(self):
        return (datetime.now() - self.last_shearing).days

    def __str__(self):
        return f"Sheep(last_shearing={self.last_shearing}, {super().__str__()})"
