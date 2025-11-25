from datetime import date
from .farm_animal import FarmAnimal
from .cage import Cage
from typing import Self

class Pig(FarmAnimal):
    def __init__(self, id: int, breed: str, born_on: date, gender: str, is_able_to_reproduce: bool, weight: float, cage: Cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)

    def __str__(self) -> str:
        return f"Pig{{{super().__str__()} }}"