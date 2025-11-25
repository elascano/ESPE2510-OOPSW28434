from datetime import datetime
from FarmAnimal import FarmAnimal
from Cage import Cage

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk: bool, liters_per_day: float, id: int, 
                 breed: str, born_on: datetime, gender: str, 
                 is_able_to_reproduce: bool, weight: float, cage: Cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self._is_producing_milk = is_producing_milk
        self._liters_per_day = liters_per_day
    
    def milk(self, milk_produced: float) -> float:
        self._liters_per_day += milk_produced
        return self._liters_per_day
    
    # Getters
    @property
    def is_producing_milk(self) -> bool:
        return self._is_producing_milk
    
    @property
    def liters_per_day(self) -> float:
        return self._liters_per_day
    
    # Setters
    @is_producing_milk.setter
    def is_producing_milk(self, is_producing_milk: bool) -> None:
        self._is_producing_milk = is_producing_milk
    
    @liters_per_day.setter
    def liters_per_day(self, liters_per_day: float) -> None:
        self._liters_per_day = liters_per_day
    
    def __str__(self) -> str:
        return (f"Cow{{is_producing_milk={self._is_producing_milk}, "
                f"liters_per_day={self._liters_per_day}, {super().__str__()}}}")