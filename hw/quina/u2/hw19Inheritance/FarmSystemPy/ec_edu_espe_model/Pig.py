"""
Author: Maryuri Quiña
Description: Farm System – Inheritance example in Python.
"""

from datetime import date
from .FarmAnimal import FarmAnimal
from .Cage import Cage


class Pig(FarmAnimal):
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
        super().__init__(animal_id, breed, born_on, gender, is_able_to_reproduce, weight, cage)

    def __str__(self) -> str:
        return f"Pig({super().__str__()})"
