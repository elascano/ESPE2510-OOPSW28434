from datetime import datetime
from FarmAnimal import FarmAnimal
from Cage import Cage

class Sheep(FarmAnimal):
    def __init__(self, last_shearing: datetime, id: int, breed: str, 
                 born_on: datetime, gender: str, is_able_to_reproduce: bool, 
                 weight: float, cage: Cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self._last_shearing = last_shearing
    
    def cut_wool(self) -> None:
        print("Cutting wool...")
    
    def shear(self) -> None:
        print("Sheep sheared successfully")
    
    # Getters
    @property
    def last_shearing(self) -> datetime:
        return self._last_shearing
    
    # Setters
    @last_shearing.setter
    def last_shearing(self, last_shearing: datetime) -> None:
        self._last_shearing = last_shearing
    
    def __str__(self) -> str:
        return f"Sheep{{last_shearing={self._last_shearing}, {super().__str__()}}}"