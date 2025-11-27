"""
Author: Maryuri Quiña
Description: Farm System – Inheritance example in Python.
"""

from datetime import date
from .FarmAnimal import FarmAnimal
from .Cage import Cage


class Sheep(FarmAnimal):
    def __init__(
        self,
        last_shearing: date,
        animal_id: int,
        breed: str,
        born_on: date,
        gender: str,
        is_able_to_reproduce: bool,
        weight: float,
        cage: Cage,
    ):
        super().__init__(animal_id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.last_shearing = last_shearing

    def __str__(self) -> str:
        return (
            f"Sheep(last_shearing={self.last_shearing}, "
            f"{super().__str__()})"
        )
