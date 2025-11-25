from abc import ABC, abstractmethod
from datetime import datetime
from Cage import Cage

class FarmAnimal(ABC):
    def __init__(self, id: int, breed: str, born_on: datetime, gender: str, 
                 is_able_to_reproduce: bool, weight: float, cage: Cage):
        self._id = id
        self._breed = breed
        self._born_on = born_on
        self._gender = gender
        self._is_able_to_reproduce = is_able_to_reproduce
        self._weight = weight
        self._cage = cage
    
    def get_age_in_months(self) -> int:
        # TODO compute the age in months
        return 0
    
    def assign_cage(self, cage: Cage) -> None:
        self._cage = cage
    
    # Getters
    @property
    def id(self) -> int:
        return self._id
    
    @property
    def breed(self) -> str:
        return self._breed
    
    @property
    def born_on(self) -> datetime:
        return self._born_on
    
    @property
    def gender(self) -> str:
        return self._gender
    
    @property
    def is_able_to_reproduce(self) -> bool:
        return self._is_able_to_reproduce
    
    @property
    def weight(self) -> float:
        return self._weight
    
    @property
    def cage(self) -> Cage:
        return self._cage
    
    # Setters
    @id.setter
    def id(self, id: int) -> None:
        self._id = id
    
    @breed.setter
    def breed(self, breed: str) -> None:
        self._breed = breed
    
    @born_on.setter
    def born_on(self, born_on: datetime) -> None:
        self._born_on = born_on
    
    @gender.setter
    def gender(self, gender: str) -> None:
        self._gender = gender
    
    @is_able_to_reproduce.setter
    def is_able_to_reproduce(self, is_able_to_reproduce: bool) -> None:
        self._is_able_to_reproduce = is_able_to_reproduce
    
    @weight.setter
    def weight(self, weight: float) -> None:
        self._weight = weight
    
    @cage.setter
    def cage(self, cage: Cage) -> None:
        self._cage = cage
    
    def __str__(self) -> str:
        return (f"FarmAnimal{{id={self._id}, breed='{self._breed}', "
                f"born_on={self._born_on}, gender='{self._gender}', "
                f"is_able_to_reproduce={self._is_able_to_reproduce}, "
                f"weight={self._weight}, cage={self._cage}}}")