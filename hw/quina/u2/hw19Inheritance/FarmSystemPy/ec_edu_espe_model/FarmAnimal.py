"""
Author: Maryuri Quiña
Description: Farm System – Inheritance example in Python.
"""

from datetime import date
from .Cage import Cage


class FarmAnimal:
    def __init__(
        self,
        animal_id: int,
        breed: str,
        born_on: date,
        gender: str,
        is_able_to_reproduce: bool,
        weight: float,
        cage: Cage,
    ):
        self.id = animal_id
        self.breed = breed
        self.born_on = born_on
        self.gender = gender
        self.is_able_to_reproduce = is_able_to_reproduce
        self.weight = weight
        self.cage = cage

    def __str__(self) -> str:
        return (
            f"FarmAnimal(id={self.id}, breed='{self.breed}', born_on={self.born_on}, "
            f"gender='{self.gender}', is_able_to_reproduce={self.is_able_to_reproduce}, "
            f"weight={self.weight}, cage={self.cage})"
        )
