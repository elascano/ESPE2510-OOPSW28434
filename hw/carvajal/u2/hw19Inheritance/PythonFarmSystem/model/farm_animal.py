from datetime import datetime
from model.cage import Cage

class FarmAnimal:
    def __init__(self, animal_id: int, breed: str, born_on: datetime, gender: str,
                 is_able_to_reproduce: bool, weight: float, cage: Cage):
        self.id = animal_id
        self.breed = breed
        self.born_on = born_on
        self.gender = gender
        self.is_able_to_reproduce = is_able_to_reproduce
        self.weight = weight
        self.cage = cage

    def __str__(self):
        return (f"FarmAnimal(id={self.id}, breed='{self.breed}', born_on={self.born_on}, "
                f"gender='{self.gender}', able={self.is_able_to_reproduce}, "
                f"weight={self.weight}, cage={self.cage})")

    def get_age_in_months(self):
        today = datetime.now()
        diff = today - self.born_on
        return diff.days // 30

    def assign_cage(self, cage: Cage):
        self.cage = cage