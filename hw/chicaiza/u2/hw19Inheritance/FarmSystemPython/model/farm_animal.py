from datetime import date
from model.cage import Cage

class FarmAnimal:
    def __init__(self, id: int, breed: str, born_on: date, gender: str,
                 is_able_to_reproduce: bool, weight: float, cage: Cage):
        self.id = id
        self.breed = breed
        self.born_on = born_on
        self.gender = gender
        self.is_able_to_reproduce = is_able_to_reproduce
        self.weight = weight
        self.cage = cage

    def get_age_in_months(self):
        today = date.today()
        return (today.year - self.born_on.year) * 12 + (today.month - self.born_on.month)

    def __str__(self):
        return (f"FarmAnimal(id={self.id}, breed={self.breed}, bornOn={self.born_on}, "
                f"gender={self.gender}, ableToReproduce={self.is_able_to_reproduce}, "
                f"weight={self.weight}, cage={self.cage})")
