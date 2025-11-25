from datetime import date
from .farm_animal import FarmAnimal
from .cage import Cage
from typing import Self

class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, laid_eggs: int, id: int, breed: str, born_on: date, gender: str, is_able_to_reproduce: bool, weight: float, cage: Cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self._is_molting = is_molting
        self._laid_eggs = laid_eggs

    def __str__(self) -> str:
        return f"Chicken{{isMolting={self.is_molting}, laidEggs={self.laid_eggs}, {super().__str__()} }}"

    def lay_an_egg(self):
        self.laid_eggs += 1

    @property
    def is_molting(self) -> bool:
        return self._is_molting

    @is_molting.setter
    def is_molting(self, value: bool):
        self._is_molting = value

    @property
    def laid_eggs(self) -> int:
        return self._laid_eggs

    @laid_eggs.setter
    def laid_eggs(self, value: int):
        self._laid_eggs = value