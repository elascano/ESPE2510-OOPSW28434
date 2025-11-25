from datetime import date
from typing import TYPE_CHECKING
if TYPE_CHECKING:
    from .cage import Cage

class FarmAnimal:
    def __init__(self, id: int, breed: str, born_on: date, gender: str, is_able_to_reproduce: bool, weight: float, cage: 'Cage' = None):
        self._id = id
        self._breed = breed
        self._born_on = born_on
        self._gender = gender
        self._is_able_to_reproduce = is_able_to_reproduce
        self._weight = weight
        self._cage = cage

    def get_age_in_months(self) -> int:
        today = date.today()
        years_diff = today.year - self.born_on.year
        months_diff = today.month - self.born_on.month
        
        if today.day < self.born_on.day:
            months_diff -= 1

        total_months = years_diff * 12 + months_diff
        
        if total_months < 0:
            return 0
            
        return total_months

    def assign_cage(self, cage: 'Cage'):
        self.cage = cage

    def __str__(self) -> str:
        return (f"FarmAnimal{{id={self.id}, breed={self.breed}, bornOn={self.born_on}, "
                f"gender={self.gender}, isAbleToReproduce={self.is_able_to_reproduce}, "
                f"weight={self.weight}, cage={self.cage}, ageInMonths={self.get_age_in_months()}}}")

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, value: int):
        self._id = value

    @property
    def breed(self) -> str:
        return self._breed

    @breed.setter
    def breed(self, value: str):
        self._breed = value

    @property
    def born_on(self) -> date:
        return self._born_on

    @born_on.setter
    def born_on(self, value: date):
        self._born_on = value

    @property
    def gender(self) -> str:
        return self._gender

    @gender.setter
    def gender(self, value: str):
        self._gender = value

    @property
    def is_able_to_reproduce(self) -> bool:
        return self._is_able_to_reproduce

    @is_able_to_reproduce.setter
    def is_able_to_reproduce(self, value: bool):
        self._is_able_to_reproduce = value

    @property
    def weight(self) -> float:
        return self._weight

    @weight.setter
    def weight(self, value: float):
        self._weight = value

    @property
    def cage(self) -> 'Cage':
        return self._cage

    @cage.setter
    def cage(self, value: 'Cage'):
        self._cage = value