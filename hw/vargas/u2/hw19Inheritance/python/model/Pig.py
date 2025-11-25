from model.FarmAnimal import FarmAnimal
class Pig(FarmAnimal):
    def __init__(self, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)

    def __str__(self):
        return f"Pig{{, {super().__str__()}}}"