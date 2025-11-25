<<<<<<< HEAD
from FarmAnimal import FarmAnimal
class Chicken(FarmAnimal):
    def __init__(self, is_molting, laid_eggs, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self._is_molting = is_molting
        self._laid_eggs = laid_eggs

    def __str__(self):
        return f"Chicken{{isMolting={self._is_molting}, laidEggs={self._laid_eggs}, {super().__str__()}}}"

    def lay_an_egg(self):
        self._laid_eggs += 1

    def is_molting(self):
        return self._is_molting

    def set_is_molting(self, is_molting):
        self._is_molting = is_molting

    def get_laid_eggs(self):
        return self._laid_eggs

    def set_laid_eggs(self, laid_eggs):
=======
from FarmAnimal import FarmAnimal
class Chicken(FarmAnimal):
    def __init__(self, is_molting, laid_eggs, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self._is_molting = is_molting
        self._laid_eggs = laid_eggs

    def __str__(self):
        return f"Chicken{{isMolting={self._is_molting}, laidEggs={self._laid_eggs}, {super().__str__()}}}"

    def lay_an_egg(self):
        self._laid_eggs += 1

    def is_molting(self):
        return self._is_molting

    def set_is_molting(self, is_molting):
        self._is_molting = is_molting

    def get_laid_eggs(self):
        return self._laid_eggs

    def set_laid_eggs(self, laid_eggs):
>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
        self._laid_eggs = laid_eggs