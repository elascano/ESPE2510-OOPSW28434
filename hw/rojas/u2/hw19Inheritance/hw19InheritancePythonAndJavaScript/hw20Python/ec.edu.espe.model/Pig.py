from FarmAnimal import FarmAnimal

class Pig(FarmAnimal):
    def __init__(self, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)

    def __str__(self):
        return (f"--> PIG:\n"
                f"    ID: {self.id}, Breed: {self.breed}, Weight: {self.weight}kg\n"
                f"    Born On: {self.born_on.date()}, Gender: {self.gender}\n"
                f"    Can Reproduce: {self.is_able_to_reproduce}, Location: {self.cage}")