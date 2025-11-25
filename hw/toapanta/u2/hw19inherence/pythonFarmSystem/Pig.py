<<<<<<< HEAD
from FarmAnimal import FarmAnimal
class Pig(FarmAnimal):
    def __init__(self, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)

    def __str__(self):
=======
from FarmAnimal import FarmAnimal
class Pig(FarmAnimal):
    def __init__(self, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)

    def __str__(self):
>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
        return f"Pig{{, {super().__str__()}}}"