from datetime import date
from .farm_animal import FarmAnimal
from .cage import Cage
from typing import Self

class Sheep(FarmAnimal):
    def __init__(self, last_sheering: date, id: int, breed: str, born_on: date, gender: str, is_able_to_reproduce: bool, weight: float, cage: Cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self._last_sheering = last_sheering

    def __str__(self) -> str:
        return f"Sheep{{lastSheering={self.last_sheering}, {super().__str__()} }}"

    def cut_whool(self):
        print("Cutting wool from the sheep...")
        self.shear()

    def shear(self):
        self.last_sheering = date.today()
        print(f"Sheep shorn. New shearing date: {self.last_sheering}")

    @property
    def last_sheering(self) -> date:
        return self._last_sheering

    @last_sheering.setter
    def last_sheering(self, value: date):
        self._last_sheering = value