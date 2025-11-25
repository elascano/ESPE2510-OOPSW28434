"""
Author: Maryuri Quiña
Description: Farm System – Inheritance example in Python.
"""

from datetime import date
from .FarmAnimal import FarmAnimal
from .Cage import Cage


class Cow(FarmAnimal):
    def __init__(
        self,
        is_producing_milk: bool,
        liters_a_day: float,
        animal_id: int,
        breed: str,
        born_on: date,
        gender: str,
        is_able_to_reproduce: bool,
        weight: float,
        cage: Cage,
    ):
        super().__init__(animal_id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.is_producing_milk = is_producing_milk
        self.liters_a_day = liters_a_day

    def __str__(self) -> str:
        return (
            f"Cow(is_producing_milk={self.is_producing_milk}, "
            f"liters_a_day={self.liters_a_day}, {super().__str__()})"
        )
