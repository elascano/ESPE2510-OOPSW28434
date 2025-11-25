"""
Author: Maryuri Quiña
Description: Farm System – Inheritance example in Python.
"""

from datetime import date
from .FarmAnimal import FarmAnimal
from .Cage import Cage


class Chicken(FarmAnimal):
    def __init__(
        self,
        is_molting: bool,
        laid_eggs: int,
        animal_id: int,
        breed: str,
        born_on: date,
        gender: str,
        is_able_to_reproduce: bool,
        weight: float,
        cage: Cage,
    ):
        super().__init__(animal_id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.is_molting = is_molting
        self.laid_eggs = laid_eggs

    def __str__(self) -> str:
        return (
            f"Chicken(is_molting={self.is_molting}, laid_eggs={self.laid_eggs}, "
            f"{super().__str__()})"
        )
