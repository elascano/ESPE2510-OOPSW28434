from datetime import date
from .farm_animal import FarmAnimal
from .cage import Cage
from typing import Self

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk: bool, litters_a_day: float, id: int, breed: str, born_on: date, gender: str, is_able_to_reproduce: bool, weight: float, cage: Cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self._is_producing_milk = is_producing_milk
        self._litters_a_day = litters_a_day

    def __str__(self) -> str:
        return f"Cow{{isProducingMilk={self.is_producing_milk}, littersADay={self.litters_a_day}, {super().__str__()} }}"

    def milk(self) -> float:
        if self.is_producing_milk:
            print(f"Cow milked: {self.litters_a_day} liters.")
            return self.litters_a_day
        else:
            print("The cow is not producing milk at this time.")
            return 0.0

    @property
    def is_producing_milk(self) -> bool:
        return self._is_producing_milk

    @is_producing_milk.setter
    def is_producing_milk(self, value: bool):
        self._is_producing_milk = value

    @property
    def litters_a_day(self) -> float:
        return self._litters_a_day

    @litters_a_day.setter
    def litters_a_day(self, value: float):
        self._litters_a_day = value