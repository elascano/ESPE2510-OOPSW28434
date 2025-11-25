from datetime import datetime
from FarmAnimal import FarmAnimal
from Cage import Cage

class Chicken(FarmAnimal):
    def __init__(self, is_molting: bool, laid_eggs: int, id: int, breed: str, 
                 born_on: datetime, gender: str, is_able_to_reproduce: bool, 
                 weight: float, cage: Cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self._is_molting = is_molting
        self._laid_eggs = laid_eggs
    
    def lay_an_egg(self) -> None:
        self._laid_eggs += 1
    
    # Getters
    @property
    def is_molting(self) -> bool:
        return self._is_molting
    
    @property
    def laid_eggs(self) -> int:
        return self._laid_eggs
    
    # Setters
    @is_molting.setter
    def is_molting(self, is_molting: bool) -> None:
        self._is_molting = is_molting
    
    @laid_eggs.setter
    def laid_eggs(self, laid_eggs: int) -> None:
        self._laid_eggs = laid_eggs
    
    def __str__(self) -> str:
        return (f"Chicken{{is_molting={self._is_molting}, laid_eggs={self._laid_eggs}, "
                f"{super().__str__()}}}")