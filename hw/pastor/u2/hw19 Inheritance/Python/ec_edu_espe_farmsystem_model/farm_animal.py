from abc import ABC, abstractmethod
from datetime import datetime
from ec_edu_espe_farmsystem_model.cage import Cage
from ec_edu_espe_farmsystem_model.location import Location

class FarmAnimal(ABC):
    def __init__(self, id, breed, born_on, gender, is_able_to_reproduce, weight, cage, location):
        self.id = id
        self.breed = breed
        self.born_on = born_on 
        self.gender = gender
        self.is_able_to_reproduce = is_able_to_reproduce
        self.weight = weight
        self.cage = cage
        self.location = location

    def get_age_in_months(self):
        today = datetime.now()
        return (today.year - self.born_on.year) * 12 + (today.month - self.born_on.month)

    def assign_cage(self, cage):
        self.cage = cage

    def __str__(self):
        type_name = self.__class__.__name__.upper()
        repro_str = "Yes" if self.is_able_to_reproduce else "No"
        cage_str = str(self.cage) if self.cage else "No Cage Assigned"
        date_str = self.born_on.strftime("%Y-%m-%d")

        return (f"\n"
                f"========================================\n"
                f"           FARM ANIMAL: {type_name}\n"
                f"========================================\n"
                f" ID              : {self.id}\n"
                f" Breed           : {self.breed}\n"
                f" Gender          : {self.gender}\n"
                f" Weight          : {self.weight} kg\n"
                f" Born On         : {date_str}\n"
                f" Reproduces      : {repro_str}\n"
                f" Location        : {self.location}\n"
                f" Cage            : {cage_str}")