from model.FarmAnimal import FarmAnimal
class Sheep(FarmAnimal):
    def __init__(self, last_shearing, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self._last_shearing = last_shearing

    def __str__(self):
        return f"Sheep{{lastShearing={self._last_shearing}, {super().__str__()}}}"

    def cut_whool(self):
        pass

    def shear(self):
        pass
    
    def get_last_shearing(self):
        return self._last_shearing

    def set_last_shearing(self, last_shearing):
        self._last_shearing = last_shearing