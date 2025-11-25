from datetime import datetime
from FarmAnimal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, id, breed, born_on, gender, is_able_to_reproduce, weight, cage, last_shearing):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.last_shearing = last_shearing

    def cut_wool(self):
        self.shear()

    def shear(self):
        self.last_shearing = datetime.now()
        print("The sheep has been sheared.")

    def __str__(self):
        if isinstance(self.last_shearing, datetime):
            shearing_date = self.last_shearing.date()
        else:
            shearing_date = self.last_shearing

        return (f"--> SHEEP:\n"
                f"    ID: {self.id}, Breed: {self.breed}, Weight: {self.weight}kg\n"
                f"    Born On: {self.born_on.date()}, Gender: {self.gender}\n"
                f"    Can Reproduce: {self.is_able_to_reproduce}, Location: {self.cage}\n"
                f"    [Specific] Last Shearing: {shearing_date}")